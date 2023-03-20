package com.hudman.wikireader.wikipedia.builder;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class QueryRandomBuilderTest {

    @Test
    void checkBuildURI() throws URISyntaxException {
        final var uri = QueryRandomBuilder.build(Language.ENGLISH, "10");
        final var expected = "https://en.wikipedia.org/w/api.php?action=query&format=json&list=random&formatversion=latest&rnnamespace=0&rnlimit=10";
        assertEquals(expected, uri.toString());
    }

}
