package com.shoryakaushik.urlShortnerBackend.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;

@Data
@Document(collection = "url")
public class Url {

    @Id
    private String id;
    private String originalUrl;
    private String shortUrl;
    private String createdBy;
    private LocalDateTime createdTime;
    private LocalDateTime expirationTime;
    private HashSet<String> accessedBy;


    public Url() {
        this.originalUrl = "";
        this.shortUrl = "";
        this.createdBy = "";
        this.createdTime = LocalDateTime.now();
        this.expirationTime = null;
        this.accessedBy = new HashSet<String>();
    }
}
