package com.hudman.wikireader.wikipedia.builder;

import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class QueryExtractBuilder {
    private final static String ACTION = "query";
    private final static String FORMAT = "json";
    private final static String PROP = "extracts";
    private final static String FORMAT_VERSION = "latest";
    private final static String EXPLAIN_TEXT = "1";
    private final static String EXSECTION_FORMAT = "wiki";

    public static List<URI> build(Language language, int[] pageId) throws URISyntaxException {
        var host = language.getValue() + "." + "wikipedia.org";
        var connectedPageId = concatPadeId(pageId);

        final var builders = IntStream.range(0, pageId.length)
                .mapToObj(it -> new URIBuilder()
                    .setScheme("https")
                    .setHost(host)
                    .setPath("/w/api.php")
                    .setParameter("action", ACTION)
                    .setParameter("format", FORMAT)
                    .setParameter("prop", PROP)
                    .setParameter("continue", "||")
                    .setParameter("pageids", connectedPageId)
                    .setParameter("formatversion", FORMAT_VERSION)
                    .setParameter("explaintext", EXPLAIN_TEXT)
                    .setParameter("exsectionformat", EXSECTION_FORMAT)
                    .setParameter("excontinue", String.valueOf(it)))
                .toList();

        List<URI> response = new ArrayList<>();
        for (var builder : builders) {
            response.add(builder.build());
        }
        return response;
    }

    private static String concatPadeId(int[] pageId) {
        var response = new StringBuilder();
        for (int count = 0; count < pageId.length; count++) {
            response.append(pageId[count]);
            if (count + 1 != pageId.length) {
                response.append("|");
            }
        }
        return response.toString();
    }
}
