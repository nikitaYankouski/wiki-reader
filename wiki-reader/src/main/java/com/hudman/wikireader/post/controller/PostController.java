package com.hudman.wikireader.post.controller;

import com.hudman.wikireader.post.controller.response.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @GetMapping
    public List<Article> getRandomArticle() {

        return null;
    }

    @PatchMapping
    public Article fixContent() {

        return null;
    }

    @PostMapping
    public ResponseEntity<String> save() {

        return null;
    }

}
