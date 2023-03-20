package com.hudman.wikireader.api.post.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private long id;
    private String name;
    private String content;
}
