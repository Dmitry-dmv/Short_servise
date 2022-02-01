package com.example.spring_servise.Service;

import com.example.spring_servise.dto.StatisticDto;
import lombok.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;

import java.util.List;

@PropertySource("classpath:link.properties")
public interface Urlservice {
    String LINK_PREFIX = "/l/";

    String getShortUrl(String original);

    String doRedirect(String link);

    List<StatisticDto> getRankedUrl(Pageable pageable);

    StatisticDto getRankedUrlByShort(String link);
}
