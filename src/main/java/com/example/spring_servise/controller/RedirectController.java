package com.example.spring_servise.controller;

import com.example.spring_servise.Service.RedirectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(RedirectController.REDIRECTION_PREFIX)
public class RedirectController {
    static final String REDIRECTION_PREFIX = "/l";
    private final RedirectionService service;

    private RedirectController(RedirectionService service) {
        this.service = service;
    }

    public static RedirectController createRedirectController(RedirectionService service) {
        return new RedirectController(service);
    }

    @GetMapping("/{link}")
    RedirectView redirect(@PathVariable String link) {
        return new RedirectView(service.doRedirect(link));
    }
}
