package com.analis.dataanalisis.controller.dto;

import java.time.Instant;

public record NewsResponse(
        String id,
        String title,
        String content,
        String author,
        Instant publishedAt,
        String url
) {

}
