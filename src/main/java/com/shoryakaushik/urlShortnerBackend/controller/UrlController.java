package com.shoryakaushik.urlShortnerBackend.controller;

import com.shoryakaushik.urlShortnerBackend.models.Url;
import com.shoryakaushik.urlShortnerBackend.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @PostMapping("/create")
    public String createUrl(@RequestBody Url url) {
        urlRepository.save(url);
        return "User Created Successfully";
    }

    @GetMapping("/get")
    public List<Url> getUrl() {
        return urlRepository.findAll();
    }
}
