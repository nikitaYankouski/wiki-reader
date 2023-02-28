package com.hudman.wikireader.post.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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
}
