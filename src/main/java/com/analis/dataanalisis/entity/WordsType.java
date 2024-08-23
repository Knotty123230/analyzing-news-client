package com.analis.dataanalisis.entity;

import java.util.Arrays;

public enum WordsType {
    CITY("CITY"),
    COUNTRY("COUNTRY"),
    LOCATION("LOCATION"),
    NATIONALITY("NATIONALITY"),
    PERSON("PERSON"),
    STATE_OR_PROVINCE("STATE_OR_PROVINSE");

    private final String stringWords;

    WordsType(String stateOrProvinse) {
        this.stringWords = stateOrProvinse;
    }

    public static WordsType fromString(String category) {
        return Arrays.stream(WordsType.values())
                .filter(c -> c.stringWords.equalsIgnoreCase(category))
                .findFirst()
                .orElse(null);
    }

    public String getStringWords() {
        return stringWords;
    }
}
