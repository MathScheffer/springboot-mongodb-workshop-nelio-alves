package com.example.workshop_mongo_nelio.resources;

import com.example.workshop_mongo_nelio.domain.Post;
import com.example.workshop_mongo_nelio.resources.util.URL;
import com.example.workshop_mongo_nelio.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public Post findById(@PathVariable String id){
        return postService.findById(id);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text){
        text = URL.decodeParam(text); // realiza o decode da url
        List<Post> posts = postService.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue = "") String text,
            @RequestParam(value="minDate", defaultValue = "") String minDate,
            @RequestParam(value="maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        Date dateMin = URL.convertDate(minDate, new Date(0)); // Se houver qualquer problema com a data enviada na requisição, retornará a data mínima = 1 de janeiro de 1970
        Date dateMax = URL.convertDate(maxDate, new Date()); // data máxima = data atual
        List<Post> posts = postService.fullSearch(text, dateMin, dateMax);
        return ResponseEntity.ok().body(posts);
    }
}
