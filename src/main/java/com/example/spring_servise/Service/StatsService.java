package com.example.spring_servise.Service;

import com.example.spring_servise.dto.ResponseStatsLink;

import java.util.List;

public interface StatsService {
    ResponseStatsLink getStatsByShortLink(String shortLink);
    List<ResponseStatsLink> getStatsByPage(int page, int count);
}
