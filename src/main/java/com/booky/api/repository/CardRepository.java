package com.booky.api.repository;

import com.booky.api.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardRepository extends MongoRepository<Card, Long> {

    List<Card> findByIdIn(List<Long> id);

}
