package com.shoryakaushik.urlShortnerBackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "url")
public class Url {

    @Id
    private Integer id;
    private String originalUrl;
    private String shortUrl;
    private Integer expirationTime;
    private List<LoggedUser> loggerUsers;
}
