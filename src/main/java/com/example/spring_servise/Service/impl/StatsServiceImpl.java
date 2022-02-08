package com.example.spring_servise.Service.impl;

import com.example.spring_servise.Service.StatsService;
import com.example.spring_servise.dto.ResponseStatsLink;
import com.example.spring_servise.repository.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StatsServiceImpl implements StatsService {
    private final LinkRepository linkRepository;
    private final PrefixShortLink prefixShortLink;

    public StatsServiceImpl(LinkRepository linkRepository, PrefixShortLink prefixShortLink) {
        this.linkRepository = linkRepository;
        this.prefixShortLink = prefixShortLink;
    }

    @Override
    public ResponseStatsLink getStatsByShortLink(String shortLink) {
        return getStreamStatsLinks()
                .filter(v -> v.getValue().getKey().equals(shortLink))
                .findFirst()
                .map(integerEntryEntry -> ResponseStatsLink.builder()
                        .link(prefixShortLink.addPrefix(shortLink))
                        .original(linkRepository.getOriginalLink(shortLink, true))
                        .rank(integerEntryEntry.getKey())
                        .count(integerEntryEntry.getValue().getValue())
                        .build())
                .orElse(null);
    }

    @Override
    public List<ResponseStatsLink> getStatsByPage(int page, int count) {
        return getStreamStatsLinks()
                .sorted(Map.Entry.comparingByKey())
                .skip((long) page * count)
                .limit(count)
                .map(integerEntryEntry -> ResponseStatsLink.builder()
                        .link(prefixShortLink.addPrefix(integerEntryEntry.getValue().getKey()))
                        .original(linkRepository.getOriginalLink(integerEntryEntry.getValue().getKey(), true))
                        .rank(integerEntryEntry.getKey())
                        .count(integerEntryEntry.getValue().getValue())
                        .build())
                .collect(Collectors.toList());
    }

    private Stream<Map.Entry<Long, Map.Entry<String, Long>>> getStreamStatsLinks() {
        Set<Map.Entry<String, Long>> allLinks = linkRepository.getAllStatsLinks().entrySet();

        AtomicLong atomicLong = new AtomicLong(getUniqueCount(allLinks) + 1);
        final long[] prevLinkCount = {0};

        return allLinks
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(entry -> {
                    long rank = (prevLinkCount[0] != entry.getValue()) ? atomicLong.decrementAndGet() : atomicLong.get();
                    prevLinkCount[0] = entry.getValue();
                    return new AbstractMap.SimpleEntry<>(rank, entry);
                });
    }

    private long getUniqueCount(Set<Map.Entry<String, Long>> allLinks) {
        return allLinks.stream()
                .map(Map.Entry::getValue)
                .distinct()
                .count();
    }
}