package com.hudman.wikireader.wikipedia.client;

import com.hudman.wikireader.wikipedia.builder.Language;
import com.hudman.wikireader.wikipedia.builder.QueryRandomBuilder;
import com.hudman.wikireader.wikipedia.response.Article;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;

@Component
public class WikipediaClient {
    private RestTemplate restTemplate;

    WikipediaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Long> getIdOfRandomArticle(Language language, String rnNameSpace, String rnLimit) {
        try {
            var uri = QueryRandomBuilder.build(language, rnNameSpace, rnLimit);
            restTemplate.getForEntity(uri, Article.class);
        } catch (URISyntaxException ex) {

        }
        return null;
    }

    public List<Article> getChosenArticleById() {
        return null;
    }

}
