package com.leoparser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum SportName {

    WATERPOLO("Waterpolo"),
    FUTSAL("Futsal"),
    SPECIALS("Specials"),
    BEACH_SOCCER("Beach Soccer"),
    TABLE_TENNIS("Table Tennis"),
    AMERICAN_FOOTBALL("American Football"),
    BASKETBALL("Basketball"),
    FLOORBALL("Floorball"),
    MOTORSPORT("Motorsport"),
    CRICKET("Cricket"),
    RUGBY_UNION("Rugby Union"),
    DARTS("Darts"),
    RUGBY_LEAGUE("Rugby League"),
    SKI_JUMPING("Ski Jumping"),
    PESAPALLO("Pesapallo"),
    GAELIC_HURLING("Gaelic Hurling"),
    MMA("MMA"),
    TENNIS("Tennis"),
    CYCLING("Cycling"),
    BOXING("Boxing"),
    GOLF("Golf"),
    VOLLEYBALL("Volleyball"),
    AUSSIE_RULES("Aussie Rules"),
    FOOTBALL("Football"),
    ESPORTS("Esports"),
    BASEBALL("Baseball"),
    ICE_HOCKEY("Ice Hockey"),
    SNOOKER("Snooker"),
    CROSS_COUNTRY("Cross-Country"),
    GAELIC_FOOTBALL("Gaelic Football"),
    HANDBALL("Handball"),
    SPEEDWAY("Speedway"),
    UNKNOWN("UNKNOWN");

    @Getter
    private final String originalName;

    public static SportName of(String value) {
        return Arrays.stream(SportName.values())
            .filter(val -> val.getOriginalName().equalsIgnoreCase(value))
            .findFirst()
            .orElse(UNKNOWN);
    }

}