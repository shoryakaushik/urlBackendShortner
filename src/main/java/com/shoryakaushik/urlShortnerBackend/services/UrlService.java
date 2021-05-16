package com.shoryakaushik.urlShortnerBackend.services;

import com.google.common.hash.Hashing;
import com.shoryakaushik.urlShortnerBackend.models.Url;
import com.shoryakaushik.urlShortnerBackend.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Url createUrl(String originalUrl, String expirationTime, HttpServletRequest request){
        Url newUrl = new Url();
        final String shortUrl = Hashing.murmur3_32().hashString(originalUrl, StandardCharsets.UTF_8).toString();
        newUrl.setCreatedBy(request.getRemoteAddr());
        newUrl.setShortUrl(shortUrl);
        newUrl.setOriginalUrl(originalUrl);
        newUrl.setExpirationTime(LocalDateTime.parse(expirationTime));
        return urlRepository.save(newUrl);
    }

    public String redirectUrl(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Url urlDto = urlRepository.findByShortUrl(url);
        if(urlDto.getExpirationTime().isBefore(LocalDateTime.now())) {
            return "Url Expired";
        }
        HashSet<String> ipSet = urlDto.getAccessedBy();
        ipSet.add(request.getRemoteAddr());
        urlDto.setAccessedBy(ipSet);
        urlRepository.save(urlDto);
        response.sendRedirect(urlDto.getOriginalUrl());
        return "Url Directed";
    }

    public List<Url> getUserUrls(HttpServletRequest request) {
        return urlRepository.findCreatedBy(request.getRemoteAddr());
    }
}
