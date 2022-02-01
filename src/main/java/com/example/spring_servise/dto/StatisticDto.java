package com.example.spring_servise.dto;

import com.example.spring_servise.model.Url;
import com.example.spring_servise.utils.Base62;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {
    private String link;
    private String original;
    private Integer rank;
    private Integer count;


    public StatisticDto(Url url) {
        this.link = UrlService.LINK_PREFIX + Base62.to(url.getId());
        this.original = url.getOriginal();
        this.rank = url.getRank();
        this.count = url.getCount();
    }
}