package com.example.spring_servise.dto;


import lombok.Value;

@Value
public class ErrorResponse {
    private String timestamp;
    private String path;
    private String message;
    private int status;
}
