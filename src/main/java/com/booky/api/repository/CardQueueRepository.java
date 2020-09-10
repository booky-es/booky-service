package com.booky.api.repository;

import com.booky.api.model.CardQueue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardQueueRepository extends MongoRepository<CardQueue, Long> {

    List<CardQueue> findByIdIn(List<Long> id);

}
