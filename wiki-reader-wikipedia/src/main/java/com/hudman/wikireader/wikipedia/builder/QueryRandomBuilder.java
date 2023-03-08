package com.hudman.wikireader.wikipedia.builder;

import org.apache.hc.core5.net.URIBuilder;

public class QueryRandomBuilder implements BaseBuilder {
    private final static String ACTION = "query";
    private final static String FORMAT = "json";
    private final static String LIST = "random";
    private final static String FORMAT_VERSION = "latest";
    private String language;
    private String rnNameSpace;
    private String rnLimit;

    public QueryRandomBuilder(Language language, String rnNameSpace, String rnLimit) {
        this.language = language.getLanguage();
        this.rnNameSpace = rnNameSpace;
        this.rnLimit = rnLimit;
    }

    @Override
    public String build() {
        var uriBuilder = new URIBuilder()
                .setScheme("https")
                .setHost(language.concat(".").concat("wikipedia.org"))
                .setPath("/w/api.php")
                .setParameter("action", ACTION)
                .setParameter("format", FORMAT)
                .setParameter("list", LIST)
                .setParameter("formatversion", FORMAT_VERSION)
                .setParameter("rnnamespace", rnNameSpace)
                .setParameter("rnlimit", rnLimit);

        return uriBuilder.toString();
    }

}
