package com.leoparser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class SportDto {

    Long id;
    SportName name;
    String _s;
    String family;
    Integer weight;
    List<RegionDto> regions;

    public void setName(String name) {
        this.name = SportName.of(name);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RegionDto {

        Long id;
        String name;
        String nameDefault;
        String family;
        String url;
        List<LeagueDto> leagues;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = PRIVATE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LeagueDto {

        Long id;
        String name;
        String nameDefault;
        String url;
        Integer weight;
        Integer prematch;
        Integer inplay;
        Integer outright;
        Boolean top;
        Integer topOrder;
        Boolean hasZeroMarginEvents;
        String logoUrl;
        String background;

    }

}



