package com.leoparser.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.leoparser.dto.MatchDto;
import com.leoparser.dto.MatchResponseDto;
import com.leoparser.dto.SportDto;
import lombok.experimental.UtilityClass;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

@UtilityClass
public class LeoClient {

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static List<SportDto> fetchSports() {
        return getAndParse("https://leon.bet/api-2/betline/sports?ctag=en-US&flags=urlv2", new TypeReference<List<SportDto>>() {});
    }

    public static MatchResponseDto fetchMatchFullData(Long matchId) {
        return getAndParse("https://leon.bet/api-2/betline/event/all?ctag=en-US&eventId=%s&flags=reg,urlv2,mm2,rrc,nodup,smgv2,outv2,wd2,dar"
            .formatted(matchId), new TypeReference<MatchResponseDto>() {});
    }

    public static MatchDto fetchMatchList(Long idEvent) {
        return getAndParse("https://leon.bet/api-2/betline/changes/all?ctag=en-US&vtag=s&league_id=%s&hideClosed=true&flags=reg,urlv2,mm2,rrc,nodup"
            .formatted(idEvent), new TypeReference<MatchDto>() {});
    }

    private static <E> E getAndParse(String url, TypeReference<E> typeReference) {
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .timeout(Duration.ofSeconds(20))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing LEON endpoint", e);
        }
    }

}
