package com.leoparser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDto {

    boolean enabled;
    int totalCount;
    String vtag;
    List<MatchData> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MatchData {

        long id;
        String _s;
        String name;
        String nameDefault;
//        List<Competitor> competitors;
        long kickoff;
        long lastUpdated;
        League league;
        String betline;
        boolean open;
        String status;
        boolean nativeMatch;
        String widgetType;
        boolean widgetVirtual;
        String url;
        String matchPhase;
        boolean hasMarketWithZeroMargin;
//        List<Market> markets;
        int runnersCount;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @FieldDefaults(level = PRIVATE)
    public static class Competitor {

        long id;
        String name;
        String homeAway;
        String logoSource;
        String logo;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @FieldDefaults(level = PRIVATE)
    public static class League {

        long id;
        Sport sport;
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
        Region region;
        String logoSource;
        String logoUrl;

    }

    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    public static class Sport {

        long id;
        String name;
        int weight;
        String family;
        List<MainMarket> mainMarkets;
        boolean virtual;
        String url;

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    public static class MainMarket {

        long id;
        String name;
        int weight;
        List<Long> altMarketTypeIds;
        boolean virtual;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @FieldDefaults(level = PRIVATE)
    public static class Region {

        long id;
        String name;
        String nameDefault;
        String family;
        String url;

    }

    @Data
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    public static class Market {

        long id;
        String typeTag;
        String name;
        long marketTypeId;
        boolean open;
        boolean hasZeroMargin;
        boolean primary;
        int cols;
        List<Runner> runners;
        Specifiers specifiers;
        List<String> selectionTypes;
        String handicap;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @FieldDefaults(level = PRIVATE)
    public static class Runner {

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
    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    public static class Specifiers {

        String goalnr;
        String total;
        String hcp;

    }

}
