package com.leoparser.service;

import com.leoparser.dto.Bet;
import com.leoparser.dto.MatchResponseDto;
import com.leoparser.dto.SportDto;
import com.leoparser.dto.TopLeagueInfo;
import com.leoparser.property.LeonLimitProperties;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.leoparser.service.LeoClient.fetchMatchFullData;
import static com.leoparser.service.LeoClient.fetchMatchList;
import static com.leoparser.service.LeoClient.fetchSports;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsLast;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ParserService implements CommandLineRunner {

    LeonLimitProperties leonLimitProperties;
    ExecutorService executorService;

    public ParserService(LeonLimitProperties leonLimitProperties) {
        this.leonLimitProperties = leonLimitProperties;
        this.executorService = Executors.newFixedThreadPool(leonLimitProperties.getThreads());
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Begin parsing...");
        log.info("limit league list: {}, limit match in league: {}, limit bets per match: {}, parallel threads: {}",
            leonLimitProperties.getLeagueList(), leonLimitProperties.getMatchListInLeague(), leonLimitProperties.getBetsPerMatch(), leonLimitProperties.getThreads());
        parse();
        log.info("Finished...");
    }

    public void parse() {
        List<SportDto> payload = fetchSports();
        List<TopLeagueInfo> sortedTopList = filterTopSportList(payload);

        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        sortedTopList
            .stream()
            .collect(Collectors.groupingBy(TopLeagueInfo::sportName))
            .forEach(((sportName, topLeagueInfos) -> tasks.add(runAsync(topLeagueInfos))));

        CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();
        executorService.shutdown();
    }

    private CompletableFuture<Void> runAsync(List<TopLeagueInfo> topLeagueInfos) {
        return CompletableFuture.runAsync(() -> {
            for (TopLeagueInfo league : topLeagueInfos) {
                fetchMatchList(league.league().getId())
                    .getData()
                    .stream()
                    .limit(leonLimitProperties.getMatchListInLeague())
                    .forEach(match -> {
                        logMatchInfo(fetchMatchFullData(match.getId()));
                    });
            }
        }, executorService);
    }

    private void logMatchInfo(MatchResponseDto dto) {
        if (dto == null || dto.getLeague() == null || dto.getMarkets() == null) return;
        System.out.println(dto.getLeague().getSport().getName() + ", " + dto.getLeague().getName());
        String matchName = dto.getName();
        String kickoffTimeUtc = Instant.ofEpochMilli(dto.getKickoff())
                                    .atZone(ZoneOffset.UTC)
                                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " UTC";

        System.out.println("\t" + matchName + ", " + kickoffTimeUtc + ", " + dto.getId());

        dto.getMarkets().stream()
            .filter(MatchResponseDto.MarketDto::isOpen)
            .filter(market -> market.getRunners() != null)
            .collect(groupingBy(MatchResponseDto.MarketDto::getName, LinkedHashMap::new, toList()))
            .entrySet()
            .stream()
            .limit(leonLimitProperties.getBetsPerMatch())
            .forEach(entry -> {
                System.out.println("\t\t" + entry.getKey());
                entry.getValue().stream()
                    .filter(MatchResponseDto.MarketDto::isOpen)
                    .forEach(runner -> {
                        runner.getRunners()
                            .stream()
                            .map(rDto -> new Bet(rDto.getName(), rDto.getPrice(), rDto.getId()))
                            .toList()
                            .forEach(bet -> System.out.printf("\t\t\t%-15s %-10s %-20s\n", bet.getName(), bet.getPrice(), bet.getId()));
                    });
            });
    }

    private List<TopLeagueInfo> filterTopSportList(List<SportDto> payload) {
        return payload.stream()
            .flatMap(sport -> sport.getRegions().stream()
                .flatMap(region -> region.getLeagues().stream()
                    .filter(league -> Boolean.TRUE.equals(league.getTop()))
                    .map(league -> new TopLeagueInfo(sport.getName(), region.getName(), league))))
            .sorted(nullsLast(Comparator.comparing(league -> league.league().getTopOrder(), naturalOrder())))
            .collect(groupingBy(TopLeagueInfo::sportName))
            .values().stream()
            .flatMap(list -> list.stream().limit(leonLimitProperties.getLeagueList()))
            .toList();
    }


}
