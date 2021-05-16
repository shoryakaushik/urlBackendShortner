package com.shoryakaushik.urlShortnerBackend.controller;

import com.shoryakaushik.urlShortnerBackend.models.Url;
import com.shoryakaushik.urlShortnerBackend.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/")
    public String home() {
        return "Welcome To Url Shortner";
    }

    @PostMapping("/create")
    public ResponseEntity<Url> createUrl(@RequestParam String originalUrl, @RequestParam String expirationTime, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(urlService.createUrl(originalUrl,expirationTime, request));
    }

    @GetMapping("/get")
    public ResponseEntity<String> redirectUrl(@RequestParam String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(urlService.redirectUrl(url,request, response));
    }

    @GetMapping("/get-all-user-urls")
    public ResponseEntity<List<Url>> getUserUrls(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(urlService.getUserUrls(request));
    }
}
