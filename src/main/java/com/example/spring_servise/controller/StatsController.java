package com.example.spring_servise.controller;

import com.example.spring_servise.Service.StatsService;
import com.example.spring_servise.dto.ResponseStatsLink;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService statsService;

    @GetMapping("/{some-short-name}")
    public ResponseStatsLink getStatsByShortLink(@PathVariable("some-short-name") String shortLink) {
        return statsService.getStatsByShortLink(shortLink);
    }

    @GetMapping
    public List<ResponseStatsLink> getCoursesByPage(@RequestParam("page") int page, @RequestParam("count") int count) {
        return statsService.getStatsByPage(page, count);
    }
}
