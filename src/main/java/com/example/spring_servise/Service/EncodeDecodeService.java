package com.example.spring_servise.Service;

public interface EncodeDecodeService {
    String encode(Long number);
    Long decode(String shortLink);
}
