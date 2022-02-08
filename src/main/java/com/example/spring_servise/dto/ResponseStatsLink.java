package com.example.spring_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseStatsLink {
    private String link;
    private String original;
    private Long rank;
    private Long count;
}
