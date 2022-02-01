package com.example.spring_servise.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatisticService {
    List<UrlStatistic> getAllRankedUrl(Pageable pageable);

    UrlStatistic getRankedUrlByShortLink(String link);
}
