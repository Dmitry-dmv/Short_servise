package com.example.spring_servise.dto;

import com.example.spring_servise.Service.UrlStatistic;
import lombok.ToString;

@ToString
public class UrlStatisticResponse {
    private final UrlStatistic statistic;
    private final String urlPrefix;

    public UrlStatisticResponse(String urlPrefix, UrlStatistic statistic) {
        this.statistic = statistic;
        this.urlPrefix = urlPrefix;
    }

    public int getRank() {
        return statistic.getRank();
    }

    public int getCount() {
        return statistic.getCount();
    }

    public String getOriginal() {
        return statistic.getOriginal();
    }

    public String getLink() {
        return urlPrefix + "/" + statistic.getLink();
    }
}
