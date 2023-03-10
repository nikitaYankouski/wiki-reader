package com.hudman.wikireader.wikipedia.builder;

import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class QueryRandomBuilder {
    private final static String ACTION = "query";
    private final static String FORMAT = "json";
    private final static String LIST = "random";
    private final static String FORMAT_VERSION = "latest";

    public static URI build(Language language, String rnNameSpace, String rnLimit) throws URISyntaxException {
        var host = language.getValue() + "." + "wikipedia.org";
        return new URIBuilder()
                .setScheme("https")
                .setHost(host)
                .setPath("/w/api.php")
                .setParameter("action", ACTION)
                .setParameter("format", FORMAT)
                .setParameter("list", LIST)
                .setParameter("formatversion", FORMAT_VERSION)
                .setParameter("rnnamespace", rnNameSpace)
                .setParameter("rnlimit", rnLimit)
                .build();
    }

}
