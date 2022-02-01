package com.example.spring_servise.Service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WrongLinkException extends RuntimeException {
    public WrongLinkException(String link, Throwable cause) {
        super(String.format("wrong link format given '%s'", link), cause);
    }
}