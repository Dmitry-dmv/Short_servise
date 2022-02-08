package com.example.spring_servise.Service.impl;

import com.example.spring_servise.Service.EncodeDecodeService;
import org.springframework.stereotype.Service;

@Service
public class EncodeDecodeServiceImpl implements EncodeDecodeService {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    @Override
    public String encode(Long number) {
        StringBuilder sb = new StringBuilder();
        while ( number > 0 ) {
            sb.append( ALPHABET.charAt((int) (number % BASE)) );
            number /= BASE;
        }
        return sb.reverse().toString();
    }

    @Override
    public Long decode(String shortLink) {
        long num = 0;
        for ( int i = 0; i < shortLink.length(); i++ )
            num = num * BASE + ALPHABET.indexOf(shortLink.charAt(i));
        return num;
    }
}
