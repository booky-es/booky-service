package com.booky.api.repository;

import com.booky.api.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public interface GroupRepository extends MongoRepository<Group, Long> {

    List<Group> findByAdminIds(BigInteger adminId);
}
