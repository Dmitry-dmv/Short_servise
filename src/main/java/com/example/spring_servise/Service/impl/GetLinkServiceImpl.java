package com.example.spring_servise.Service.impl;

import com.example.spring_servise.Service.EncodeDecodeService;
import com.example.spring_servise.Service.GetLinkService;
import com.example.spring_servise.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class GetLinkServiceImpl implements GetLinkService {

        private final LinkRepository linkRepository;
        private final EncodeDecodeService encodeDecodeService;

    @Override
        public String generateShortLink(String originalLink) {

            String shortLink = linkRepository.getShortLink(originalLink);
            if (shortLink != null) {
                return shortLink;
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            shortLink = encodeDecodeService.encode(timestamp.getTime());
            linkRepository.saveLink(originalLink, shortLink);
            return shortLink;
        }

        @Override
        public String getOriginalLink(String shortLink) {
            return linkRepository.getOriginalLink(shortLink, false);
        }
    }

