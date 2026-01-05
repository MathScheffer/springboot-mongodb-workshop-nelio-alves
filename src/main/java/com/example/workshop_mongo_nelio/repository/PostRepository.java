package com.example.workshop_mongo_nelio.repository;

import com.example.workshop_mongo_nelio.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    //?0 = primeiro parametro que receber no requestparams;
    //options = i: ignore case
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text); //método customizado implementado em PostRepositoryImpl
    List<Post> findByTitleContainingIgnoreCase(String text); //Spring data gera automaticamente o método conforme a documentação

    @Query("{ $and: [{ date: {$gte: ?1} }, { date: { $lte: ?2 } }, { $or: [{ 'title': { $regex: ?0, $options: 'i' } } , { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } }] }]}")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);

}
