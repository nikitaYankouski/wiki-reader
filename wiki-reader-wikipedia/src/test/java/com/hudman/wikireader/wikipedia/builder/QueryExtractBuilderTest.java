package com.hudman.wikireader.wikipedia.builder;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryExtractBuilderTest {

    long[] pageId = new long[] { 58143961, 62576755, 3752653, 36554043, 51135969 };

    @Test
    void checkBuildURI() throws URISyntaxException {
        var uri = QueryExtractBuilder.build(Language.ENGLISH, pageId);

        var expected = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&pageids=58143961%7C62576755%7C3752653%7C36554043%7C51135969&formatversion=latest&explaintext=1&exsectionformat=wiki";

        assertEquals(expected, uri.toString());
    }

    @Test
    void checkBuildContinueURI() throws URISyntaxException {
        var uri = QueryExtractBuilder.build(Language.ENGLISH, pageId);
        var uriContinue = QueryExtractBuilder.buildContinue(uri, pageId, 1);

        var expectedContinue = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&continue=%7C%7C&pageids=58143961%7C62576755%7C3752653%7C36554043%7C51135969&formatversion=latest&explaintext=1&exsectionformat=wiki&excontinue=1";

        assertEquals(expectedContinue, uriContinue.toString());
    }
}
