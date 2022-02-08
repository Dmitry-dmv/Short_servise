package com.example.spring_servise.repository.impl;

import com.example.spring_servise.repository.LinkRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LinkRepositoryImpl implements LinkRepository {
    private final Map<String, String> links = new HashMap<>();
    private final Map<String, Long> stats = new HashMap<>();

    @Override
    public String getShortLink(String originalLink) {
        return links.get(originalLink);
    }

    @Override
    public String getOriginalLink(String shortLink, boolean fromStat) {
        if (!fromStat) {
            stats.merge(shortLink, 1L, Long::sum);
        }
        return links.get(shortLink);
    }

    @Override
    public void saveLink(String originalLink, String shortLink) {
        links.put(originalLink, shortLink);
        links.put(shortLink, originalLink);
    }

    @Override
    public Map<String, Long> getAllStatsLinks() {
        return stats;
    }
}
