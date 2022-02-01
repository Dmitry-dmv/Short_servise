package com.example.spring_servise.controller;

import com.example.spring_servise.entity.Shorter;
import com.example.spring_servise.repository.ShorterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ShorterController {
    Logger logger = LoggerFactory.getLogger(ShorterController.class.getSimpleName());

    private final ShorterRepository repository;


    @Autowired
    public ShorterController(final ShorterRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/")
    public String createShortUrl(String original) {

        return null;
    }

    @GetMapping(path = "/{hash}")
    public ResponseEntity redirectShorter(@PathVariable("hash") String hash) {

        return null;

    }
}

