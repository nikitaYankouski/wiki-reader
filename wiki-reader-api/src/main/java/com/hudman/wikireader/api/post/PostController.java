package com.hudman.wikireader.api.post;

import com.hudman.wikireader.api.post.response.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
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
