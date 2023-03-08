package com.hudman.wikireader.wikipedia.builder;

import org.apache.hc.core5.net.URIBuilder;

import java.util.List;

class QueryExtractBuilder {
    private final static String ACTION = "query";
    private final static String FORMAT = "json";
    private final static String PROP = "extracts";
    private final static String FORMAT_VERSION = "latest";
    private final static String EXPLAIN_TEXT = "1";
    private final static String EXSECTION_FORMAT = "wiki";

    private String language;
    private List<Long> padeIds;

    public QueryExtractBuilder(Language language, List<Long> padeIds) {
        this.language = language.getLanguage();
        this.padeIds = padeIds;
    }

    public String build() {
        var uriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost(language.concat(".").concat("wikipedia.org"))
                .setPath("/w/api.php")
                .setParameter("action", ACTION)
                .setParameter("format", FORMAT)
                .setParameter("prop", PROP)
                .setParameter("padeids", String.valueOf(padeIds.get(0)))
                .setParameter("formatversion", FORMAT_VERSION)
                .setParameter("explaintext", EXPLAIN_TEXT)
                .setParameter("exsectionformat", EXSECTION_FORMAT);

        return uriBuilder.toString();
    }
}
