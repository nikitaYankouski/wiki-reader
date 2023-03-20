package com.hudman.wikireader.wikipedia.builder;

import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class QueryRandomBuilder {
    private final static String ACTION = "query";
    private final static String FORMAT = "json";
    private final static String LIST = "random";
    private final static String FORMAT_VERSION = "latest";
    private final static String RN_NAME_SPASE = "0";

    public static URI build(Language language, String rnLimit) throws URISyntaxException {
        var host = language.getValue() + "." + "wikipedia.org";
        return new URIBuilder()
                .setScheme("https")
                .setHost(host)
                .setPath("/w/api.php")
                .setParameter("action", ACTION)
                .setParameter("format", FORMAT)
                .setParameter("list", LIST)
                .setParameter("formatversion", FORMAT_VERSION)
                .setParameter("rnnamespace", RN_NAME_SPASE)
                .setParameter("rnlimit", rnLimit)
                .build();
    }

}
