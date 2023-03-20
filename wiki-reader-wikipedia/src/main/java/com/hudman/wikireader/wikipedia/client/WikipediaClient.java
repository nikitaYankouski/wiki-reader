package com.hudman.wikireader.wikipedia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hudman.wikireader.wikipedia.builder.Language;
import com.hudman.wikireader.wikipedia.builder.QueryExtractBuilder;
import com.hudman.wikireader.wikipedia.builder.QueryRandomBuilder;
import com.hudman.wikireader.wikipedia.parser.WikiParser;
import com.hudman.wikireader.wikipedia.response.Article;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;

@Component
public class WikipediaClient {
    private RestTemplate restTemplate;

    private WikiParser wikiParser;

    WikipediaClient(RestTemplate restTemplate, WikiParser wikiParser) {
        this.restTemplate = restTemplate;
        this.wikiParser = wikiParser;
    }

    public List<Integer> getIdOfRandomArticle(Language language, String rnLimit) {
        try {
            final var uri = QueryRandomBuilder.build(language, rnLimit);
            final var responseWiki = restTemplate.getForEntity(uri, String.class);

            final var body = responseWiki.getBody();
            return wikiParser.parseToRandomArticle(body);
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Article> getChosenArticleById(Language language, List<Integer> id) {
        return null;
    }

}
