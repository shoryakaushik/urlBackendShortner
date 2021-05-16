package com.shoryakaushik.urlShortnerBackend.controller;

import com.shoryakaushik.urlShortnerBackend.dto.Url;
import com.shoryakaushik.urlShortnerBackend.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.google.common.hash.Hashing;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public String createUrl(@RequestBody Url url, HttpServletRequest request) {
        Url newUrl = new Url();
        final String shortUrl = Hashing.murmur3_32().hashString(url.getOriginalUrl(), StandardCharsets.UTF_8).toString();
        newUrl.setCreatedBy(request.getRemoteAddr());
        newUrl.setShortUrl(shortUrl);
        newUrl.setOriginalUrl(url.getOriginalUrl());
        newUrl.setExpirationTime(url.getExpirationTime());
        urlRepository.save(newUrl);
        return "Url Created Successfully";
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public String redirectUrl(@RequestParam String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Url urlDto = urlRepository.findByShortUrl(url);
        if(urlDto.getExpirationTime().isBefore(LocalDateTime.now())) {
            return "Url Expired";
        }
        HashSet<String> ipSet = urlDto.getAccessedBy();
        ipSet.add(request.getRemoteAddr());
        urlRepository.save(urlDto);
        response.sendRedirect(urlDto.getOriginalUrl());
        return url;
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<Url> getAllUrls(HttpServletRequest request) {
        return urlRepository.findAll();
    }

    @GetMapping("/get-ip")
    @ResponseStatus(HttpStatus.OK)
    public String getUserIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @GetMapping("/get-all-user-urls")
    @ResponseStatus(HttpStatus.OK)
    public List<Url> getAllUserUrls(HttpServletRequest request) {
        return urlRepository.findCreatedBy(request.getRemoteAddr());
    }
}
