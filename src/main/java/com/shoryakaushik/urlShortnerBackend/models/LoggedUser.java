package com.shoryakaushik.urlShortnerBackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "loggedUser")
public class LoggedUser {
    @Id
    private Integer id;
    private String ip;
    private List<Url> followedUrls;
}
