package com.example.workshop_mongo_nelio.repository;

import com.example.workshop_mongo_nelio.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
