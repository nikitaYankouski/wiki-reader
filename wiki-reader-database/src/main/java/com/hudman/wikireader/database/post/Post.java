package com.hudman.wikireader.database.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "article_id")
    private long articleId;

    @Column(name = "name")
    private String name;

    @Column(name = "date_post")
    private LocalDateTime datePost;

    public Post(long articleId, String name, LocalDateTime datePost) {
        this.articleId = articleId;
        this.name = name;
        this.datePost = datePost;
    }
}
