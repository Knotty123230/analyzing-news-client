package com.analis.dataanalisis.entity;

import java.util.Arrays;

public enum Category {
    ARTS("ARTS"),
    ARTS_CULTURE("ARTS & CULTURE"),
    BLACK_VOICES("BLACK VOICES"),
    BUSINESS("BUSINESS"),
    COLLEGE("COLLEGE"),
    COMEDY("COMEDY"),
    CRIME("CRIME"),
    CULTURE_ARTS("CULTURE & ARTS"),
    DIVORCE("DIVORCE"),
    EDUCATION("EDUCATION"),
    ENTERTAINMENT("ENTERTAINMENT"),
    ENVIRONMENT("ENVIRONMENT"),
    FIFTY("FIFTY"),
    FOOD_DRINK("FOOD & DRINK"),
    GOOD_NEWS("GOOD NEWS"),
    GREEN("GREEN"),
    HEALTHY_LIVING("HEALTHY LIVING"),
    HOME_LIVING("HOME & LIVING"),
    IMPACT("IMPACT"),
    LATINO_VOICES("LATINO VOICES"),
    MEDIA("MEDIA"),
    MONEY("MONEY"),
    PARENTING("PARENTING"),
    PARENTS("PARENTS"),
    POLITICS("POLITICS"),
    QUEER_VOICES("QUEER VOICES"),
    RELIGION("RELIGION"),
    SCIENCE("SCIENCE"),
    SPORTS("SPORTS"),
    STYLE("STYLE"),
    STYLE_BEAUTY("STYLE & BEAUTY"),
    TASTE("TASTE"),
    TECH("TECH"),
    THE_WORLDPOST("THE WORLDPOST"),
    TRAVEL("TRAVEL"),
    US_NEWS("U.S. NEWS"),
    WEDDINGS("WEDDINGS"),
    WEIRD_NEWS("WEIRD NEWS"),
    WELLNESS("WELLNESS"),
    WOMEN("WOMEN"),
    WORLD_NEWS("WORLD NEWS"),
    WORLDPOST("WORLDPOST"),
    UNKNOWN("UNKNOWN");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public static Category fromString(String category) {
        return Arrays.stream(Category.values())
                .filter(c -> c.displayName.equalsIgnoreCase(category))
                .findFirst()
                .orElse(Category.UNKNOWN);
    }

    public String getDisplayName() {
        return displayName;
    }
}
