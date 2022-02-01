package com.example.spring_servise.Service;

import com.example.spring_servise.model.RankedUrl;
import lombok.Value;

@Value
public class UrlStatistic {
    private final int rank;
    private final int count;
    private final String original;
    private final String link;

    public UrlStatistic(RankedUrl rankedUrl, String link) {
        this.rank = rankedUrl.getRank();
        this.count = rankedUrl.getRedirects();
        this.original = rankedUrl.getOriginal();
        this.link = link;
    }
}
