package com.status.repository;

import com.status.model.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StatusRepository extends MongoRepository<Status,String> {
    List<Status> findAllByuserId();
}
