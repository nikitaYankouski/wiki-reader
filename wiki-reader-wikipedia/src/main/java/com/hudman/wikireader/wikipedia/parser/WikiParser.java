package com.hudman.wikireader.wikipedia.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hudman.wikireader.wikipedia.response.Article;
import com.hudman.wikireader.wikipedia.response.RandomArticle;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class WikiParser {

    private ObjectMapper mapper;

    private Configuration configuration;

    private static final String POINTER_RANDOM = "/query/random";

    WikiParser(ObjectMapper objectMapper, Configuration configuration) {
        this.mapper = objectMapper;
        this.configuration = configuration;
    }

    public List<RandomArticle> parseToRandomArticle(String json) throws JsonProcessingException {
        final var jsonDeep = toDeep(json, POINTER_RANDOM);
        return mapper.readValue(jsonDeep, new TypeReference<>() {});
    }

    public List<Article> parseToArticle(List<String> json) {
        return IntStream.range(0, json.size()).mapToObj(it -> {
            final var path = String.format("$.query.pages[%d]", it);
            return JsonPath.using(configuration).parse(json.get(it)).read(path, Article.class);
        }).toList();
    }

    private String toDeep(String json, String path) throws JsonProcessingException {
        return mapper.readTree(json).at(path).toPrettyString();
    }
}
