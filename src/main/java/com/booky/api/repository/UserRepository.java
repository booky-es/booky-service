package com.booky.api.repository;

import com.booky.api.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User, BigInteger> {

    List<User> findByFirstNameStartsWith(String match);

    List<User> findByLastNameStartsWith(String match);

    List<User> findByUserIdIn(List<BigInteger> ids);

    List<User> findByEmail(String email);
}
