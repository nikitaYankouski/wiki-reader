package com.hudman.wikireader.wikipedia.builder;

import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class QueryExtractBuilder {
    private final static String ACTION = "query";
    private final static String FORMAT = "json";
    private final static String PROP = "extracts";
    private final static String FORMAT_VERSION = "latest";
    private final static String EXPLAIN_TEXT = "1";
    private final static String EXSECTION_FORMAT = "wiki";

    public static URI build(Language language, long[] pageId) throws URISyntaxException {
        var host = language.getValue() + "." + "wikipedia.org";
        var connectedPageId = concatPadeId(pageId);

        return new URIBuilder()
                .setScheme("https")
                .setHost(host)
                .setPath("/w/api.php")
                .setParameter("action", ACTION)
                .setParameter("format", FORMAT)
                .setParameter("prop", PROP)
                .setParameter("pageids", connectedPageId)
                .setParameter("formatversion", FORMAT_VERSION)
                .setParameter("explaintext", EXPLAIN_TEXT)
                .setParameter("exsectionformat", EXSECTION_FORMAT)
                .build();
    }

    public static URI buildContinue(URI uri, long[] pageId, int number) throws URISyntaxException {
        var scheme = uri.getScheme();
        var host = uri.getHost();
        var path = uri.getPath();
        var connectedPageId = concatPadeId(pageId);

        return new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(path)
                .setParameter("action", ACTION)
                .setParameter("format", FORMAT)
                .setParameter("prop", PROP)
                .setParameter("continue", "||")
                .setParameter("pageids", connectedPageId)
                .setParameter("formatversion", FORMAT_VERSION)
                .setParameter("explaintext", EXPLAIN_TEXT)
                .setParameter("exsectionformat", EXSECTION_FORMAT)
                .setParameter("excontinue", String.valueOf(number))
                .build();
    }

    private static String concatPadeId(long[] pageId) {
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
