package com.hudman.wikireader.wikipedia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hudman.wikireader.wikipedia.builder.Language;
import com.hudman.wikireader.wikipedia.builder.QueryExtractBuilder;
import com.hudman.wikireader.wikipedia.builder.QueryRandomBuilder;
import com.hudman.wikireader.wikipedia.parser.WikipediaParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URISyntaxException;
import java.util.List;

@Component
public class WikipediaClient {
    private RestTemplate restTemplate;
    private WikipediaParser wikipediaParser;

    WikipediaClient(RestTemplate restTemplate, WikipediaParser wikipediaParser) {
        this.restTemplate = restTemplate;
        this.wikipediaParser = wikipediaParser;
    }

    public List<Integer> getRandomIdsOfArticles(Language language, String rnLimit) {
        try {
            final var uri = QueryRandomBuilder.build(language, rnLimit);
            final var responseWiki = restTemplate.getForEntity(uri, String.class);

            final var body = responseWiki.getBody();
            return wikipediaParser.parseToRandomArticle(body);
        } catch (URISyntaxException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    public String getChosenArticlesById(Language language, List<Integer> id) {
        try {
            final var ids = id.stream().mapToInt(Integer::valueOf).toArray();
            final var preparedUriList = QueryExtractBuilder.build(language, ids);

            final var jsonList = preparedUriList.stream()
                    .map(uri -> restTemplate.getForEntity(uri, String.class).getBody()).toList();
            final var articleList = wikipediaParser.parseToArticle(jsonList);
            return wikipediaParser.parseToJson(articleList);
        } catch (URISyntaxException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (JsonProcessingException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

}
