package com.booky.api.repository;

import com.booky.api.model.URL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends MongoRepository<URL, String> {

}
