package com.hudman.wikireader.wikipedia.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class WikiParserTest {

    WikiParser wikiParser;

    Configuration configuration;

    WikiParserTest() {
        configuration = com.jayway.jsonpath.Configuration
                .builder()
                .jsonProvider(new JacksonJsonProvider())
                .mappingProvider(new JacksonMappingProvider())
                .build();
        this.wikiParser = new WikiParser(new ObjectMapper(), configuration);
    }

    static final ClassLoader classLoader = WikiParserTest.class.getClassLoader();

    @Test
    void checkParseToRandomArticle() throws JsonProcessingException {
        final var json = uploadTestData(List.of("wikiparser/randomarticle/00-random.json"));
        final var randomArticle = wikiParser.parseToRandomArticle(json.get(0));

        assertEquals(40284997, randomArticle.get(0));
        assertEquals(63834655, randomArticle.get(1));
        assertEquals(54246063, randomArticle.get(2));
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
        final var actual = wikiParser.parseToArticle(json);

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
