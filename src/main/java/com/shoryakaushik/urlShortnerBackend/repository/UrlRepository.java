package com.shoryakaushik.urlShortnerBackend.repository;

import com.shoryakaushik.urlShortnerBackend.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UrlRepository extends MongoRepository<Url, String> {

    @Query("{ 'shortUrl' : ?0 }")
    Url findByShortUrl(String shortUrl);

    @Query("{ 'createdBy' : ?0 }")
    List<Url> findCreatedBy(String createdBy);
}
