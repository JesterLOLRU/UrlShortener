package com.tsvirko.urlshortener.controller;

import com.tsvirko.urlshortener.service.ShortenerUrlService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/url")
@RestController
@Tag(name = "URL Shortener Controller", description = "Controller for URL Shortener")
public class ShortenerController {

    @Autowired
    private ShortenerUrlService shortenerUrlService;

    @GetMapping(path = "/short")
    public String shortUrl(@RequestParam String url) {
        return shortenerUrlService.shortURL(url);
    }

    @GetMapping(path = "/{url}")
    public ResponseEntity<Void> redirect(@PathVariable String url) {
        return shortenerUrlService.getUrlForRedirect(url);
    }
}
