package com.leoparser.dto;

public record TopLeagueInfo(
    SportName sportName,
    String leagueName,
    SportDto.LeagueDto league) {

}