package com.status.repository;

import com.status.model.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepo extends MongoRepository<Comments,String> {
}
