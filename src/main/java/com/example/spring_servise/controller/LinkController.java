package com.example.spring_servise.controller;

import com.example.spring_servise.Service.GetLinkService;
import com.example.spring_servise.Service.impl.PrefixShortLink;
import com.example.spring_servise.dto.RequestOriginalLink;
import com.example.spring_servise.dto.ResponseShortLink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LinkController<ResponeShortLink> {
    private final GetLinkService shortLinkService;
    private final PrefixShortLink prefixShortLink;

    @PostMapping("/generate")
    public ResponseShortLink generate(@RequestBody RequestOriginalLink requestOriginalLink) {
        String shortLink = shortLinkService.generateShortLink(requestOriginalLink.getOriginal());
        return ResponseShortLink.builder().link(prefixShortLink.addPrefix(shortLink)).build();


    }

    @GetMapping("/1/some-short-name")
    public ResponseEntity<Void> redirectToOriginalLink(@PathVariable("some-short-name") String shortLink) {
        String originalLink = shortLinkService.getOriginalLink(shortLink);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .header(HttpHeaders.LOCATION, originalLink)
                .build();
    }
}