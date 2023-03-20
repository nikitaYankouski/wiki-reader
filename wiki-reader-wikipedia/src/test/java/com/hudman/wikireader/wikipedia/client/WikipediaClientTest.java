package com.hudman.wikireader.wikipedia.client;

import com.hudman.wikireader.wikipedia.builder.Language;
import com.hudman.wikireader.wikipedia.parser.WikipediaParserTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WikipediaClientTest {

    @Autowired
    WikipediaClient wikipediaClient;

    static final ClassLoader classLoader = WikipediaClientTest.class.getClassLoader();

    @Test
    void getRandomIdsOfArticlesTest() {
        final var ids = wikipediaClient.getRandomIdsOfArticles(Language.ENGLISH, "10");

        assertEquals(10, ids.size());
    }

    @Test
    void getChosenArticlesByIdTest() {
        final var response = wikipediaClient.getChosenArticlesById(Language.ENGLISH, List.of(3752653, 36554043));
        final var expected = uploadTestData(List.of("wikiparser/article/05-page.json"));

        assertEquals(expected.get(0), response);
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
