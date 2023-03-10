package com.hudman.wikireader.wikipedia.builder;

public enum Language {
    ENGLISH("en"),
    POLISH("pl"),
    RUSSIAN("ru"),
    JAPANESE("ja"),
    GERMAN("de"),
    FRENCH("fr"),
    SPANISH("es"),
    ITALIAN("it"),
    CHINESE("zh");

    private String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
