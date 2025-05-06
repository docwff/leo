package com.leoparser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchResponseDto {
    long id;
    String _s;
    String name;
    String nameDefault;
    List<CompetitorDto> competitors;
    long kickoff;
    long lastUpdated;
    LeagueDto league;
    String betline;
    boolean open;
    String status;
    boolean nativeField; // renamed from "native" to avoid keyword conflict
    String widgetType;
    boolean widgetVirtual;
    String url;
    String matchPhase;
    boolean hasMarketWithZeroMargin;
    List<MarketDto> markets;
    int runnersCount;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CompetitorDto {
        long id;
        String name;
        String homeAway;
        String logoSource;
        String logo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LeagueDto {
        long id;
        SportDto sport;
        String name;
        String nameDefault;
        String url;
        int weight;
        int prematch;
        int inplay;
        int outright;
        boolean top;
        boolean hasZeroMarginEvents;
        int topOrder;
        RegionDto region;
        String logoSource;
        String logoUrl;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SportDto {
        long id;
        String name;
        int weight;
        String family;
        List<MainMarketDto> mainMarkets;
        boolean virtual;
        String url;
        List<MarketGroupDto> marketGroups;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainMarketDto {
        long id;
        String name;
        int weight;
        List<Long> altMarketTypeIds;
        boolean virtual;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MarketGroupDto {
        long id;
        String name;
        List<Long> marketTypeIds;
        boolean virtual;
        StyleDto style;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StyleDto {
        String type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RegionDto {
        long id;
        String name;
        String nameDefault;
        String family;
        String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MarketDto {
        long id;
        String typeTag;
        String name;
        long marketTypeId;
        boolean open;
        boolean hasZeroMargin;
        boolean primary;
        int cols;
        List<RunnerDto> runners;
        SpecifiersDto specifiers;
        List<String> selectionTypes;
        String handicap;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RunnerDto {
        long id;
        String name;
        boolean open;
        int r;
        int c;
        List<String> tags;
        double price;
        String priceStr;
        String handicap;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SpecifiersDto {
        String total;
        String goalnr;
        String hcp;
        String variant;
        String from;
        String to;
        String cornernr;
        String bookingnr;
    }
}