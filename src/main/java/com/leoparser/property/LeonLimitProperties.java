package com.leoparser.property;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "leon.limit")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LeonLimitProperties {

    int leagueList;
    int matchListInLeague;
    int betsPerMatch;
    int threads;

}