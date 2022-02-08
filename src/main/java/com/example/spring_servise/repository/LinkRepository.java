package com.example.spring_servise.repository;

import java.util.Map;

public interface LinkRepository {
    String getShortLink(String originalLink);
    String getOriginalLink(String shortLink, boolean fromStat);
    void saveLink(String originalLink, String shortLink);

    Map<String, Long> getAllStatsLinks();
}
