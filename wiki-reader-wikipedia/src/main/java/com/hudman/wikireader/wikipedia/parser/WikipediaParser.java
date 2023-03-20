package com.hudman.wikireader.wikipedia.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hudman.wikireader.wikipedia.response.Article;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class WikipediaParser {
    private ObjectMapper mapper;
    private Configuration configuration;
    private static final String PATH_RANDOM = "$.query.random[*].id";

    WikipediaParser(ObjectMapper objectMapper, Configuration configuration) {
        this.mapper = objectMapper;
        this.configuration = configuration;
    }

    public List<Integer> parseToRandomArticle(String json) {
        return JsonPath.using(configuration).parse(json).read(PATH_RANDOM);
    }

    public List<Article> parseToArticle(List<String> json) {
        return IntStream.range(0, json.size()).mapToObj(it -> {
            final var path = String.format("$.query.pages[%d]", it);
            return JsonPath.using(configuration).parse(json.get(it)).read(path, Article.class);
        }).toList();
    }

    public String parseToJson(List<Article> articleList) throws JsonProcessingException {
        return mapper.writeValueAsString(articleList);
    }
}
