package com.hudman.wikireader.wikipedia;

import com.hudman.wikireader.wikipedia.builder.Language;
import com.hudman.wikireader.wikipedia.builder.QueryRandomBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class QueryRandomBuilderTest {

    @Test
    void checkBuildURI() {
        final var builder = new QueryRandomBuilder(Language.ENGLISH, "0", "10");

        final var uri = builder.build();

        final var expected = "https://en.wikipedia.org/w/api.php?action=query&format=json&list=random&formatversion=latest&rnnamespace=0&rnlimit=10";

        assertEquals(expected, uri);
    }

}
