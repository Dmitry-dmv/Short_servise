package com.example.spring_servise.Service;

public interface GetLinkService {
    String generateShortLink(String originalLink);
    String getOriginalLink(String shortLink);
}
