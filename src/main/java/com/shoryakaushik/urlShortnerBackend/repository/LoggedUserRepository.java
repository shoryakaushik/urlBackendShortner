package com.shoryakaushik.urlShortnerBackend.repository;

import com.shoryakaushik.urlShortnerBackend.models.LoggedUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoggedUserRepository extends MongoRepository<LoggedUser, Integer> {
}
