package com.shoryakaushik.urlShortnerBackend.repository;

import com.shoryakaushik.urlShortnerBackend.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<Url, Integer> {
}
