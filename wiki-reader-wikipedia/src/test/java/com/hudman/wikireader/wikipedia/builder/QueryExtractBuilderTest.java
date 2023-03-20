package com.hudman.wikireader.wikipedia.builder;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryExtractBuilderTest {
    int[] pageId = new int[] { 58143961, 62576755, 3752653, 36554043, 51135969 };

    @Test
    void checkBuildURI() throws URISyntaxException {
        final var uri = QueryExtractBuilder.build(Language.ENGLISH, pageId);

        final var expected_0 = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&continue=%7C%7C&pageids=58143961%7C62576755%7C3752653%7C36554043%7C51135969&formatversion=latest&explaintext=1&exsectionformat=wiki&excontinue=0";
        final var expected_1 = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&continue=%7C%7C&pageids=58143961%7C62576755%7C3752653%7C36554043%7C51135969&formatversion=latest&explaintext=1&exsectionformat=wiki&excontinue=1";
        final var expected_4 = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&continue=%7C%7C&pageids=58143961%7C62576755%7C3752653%7C36554043%7C51135969&formatversion=latest&explaintext=1&exsectionformat=wiki&excontinue=4";

        assertEquals(expected_0, uri.get(0).toString());
        assertEquals(expected_1, uri.get(1).toString());
        assertEquals(expected_4, uri.get(4).toString());
    }
}
