package com.hudman.wikireader.wikipedia.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hudman.wikireader.wikipedia.response.Article;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class WikipediaParserTest {

    WikipediaParser wikipediaParser;

    Configuration configuration;

    WikipediaParserTest() {
        configuration = com.jayway.jsonpath.Configuration
                .builder()
                .jsonProvider(new JacksonJsonProvider())
                .mappingProvider(new JacksonMappingProvider())
                .build();
        this.wikipediaParser = new WikipediaParser(new ObjectMapper(), configuration);
    }

    static final ClassLoader classLoader = WikipediaParserTest.class.getClassLoader();

    @Test
    void checkParseToRandomArticle() {
        final var json = uploadTestData(List.of("wikiparser/randomarticle/00-random.json"));
        final var randomArticle = wikipediaParser.parseToRandomArticle(json.get(0));

        assertEquals(40284997, randomArticle.get(0));
        assertEquals(63834655, randomArticle.get(1));
        assertEquals(54246063, randomArticle.get(2));
    }
    @Test
    void checkParseToJson() throws JsonProcessingException {
        final var articleList =
                List.of(
                        new Article(322323, "title_0", "content_0"),
                        new Article(432432, "title_1", "content_1"),
                        new Article(903292, "title_2", "content_2")
                );

        final var response = wikipediaParser.parseToJson(articleList);
        final var expected = "[{\"title\":\"title_0\",\"pageid\":322323,\"extract\":\"content_0\"},{\"title\":\"title_1\",\"pageid\":432432,\"extract\":\"content_1\"},{\"title\":\"title_2\",\"pageid\":903292,\"extract\":\"content_2\"}]";

        assertEquals(expected, response);
    }
    @Test
    void checkParseToArticle() {
        final var listPath = Arrays.asList(
                "wikiparser/article/00-page.json",
                "wikiparser/article/01-page.json",
                "wikiparser/article/02-page.json",
                "wikiparser/article/03-page.json",
                "wikiparser/article/04-page.json"
        );

        final var json = uploadTestData(listPath);
        final var actual = wikipediaParser.parseToArticle(json);

        assertEquals("Junebug (film)", actual.get(0).getTitle());
        assertTrue(actual.get(0).getContent().startsWith("Junebug is a 2005 American comedy-drama"));

        assertEquals("Sore dewa, Mata Ashita", actual.get(1).getTitle());
        assertTrue(actual.get(1).getContent().startsWith("\"Sore dewa, Mata Ashita\" (それでは、また明日, Well Then, See You Tomorrow))"));

        assertEquals("Gilbert Nicolas", actual.get(2).getTitle());
        assertTrue(actual.get(2).getContent().startsWith("Gilbert Nicolas (c. 1462 – 27 August 1532)"));

        assertEquals("IlvH RNA motif", actual.get(3).getTitle());
        assertTrue(actual.get(3).getContent().startsWith("The ilvH RNA motif is a conserved RNA"));

        assertEquals("1958–59 St. Francis Terriers men's basketball team", actual.get(4).getTitle());
        assertTrue(actual.get(4).getContent().startsWith("The 1958–1959 St. Francis Terriers men's basketball"));
    }

    private List<String> uploadTestData(List<String> paths) {
        return paths.stream().map(path -> {
            final var file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
            try {
                return new String(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
}
