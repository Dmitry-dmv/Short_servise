package com.example.spring_servise.model;

public interface RankedUrl {
    int getRank();
    String getOriginal();

    int getRedirects();

    int getId();
}
