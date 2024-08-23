package com.analis.dataanalisis.api;

import com.analis.dataanalisis.controller.dto.NewsResponse;
import com.analis.dataanalisis.entity.AnalyzedNews;
import com.analis.dataanalisis.entity.Category;
import com.analis.dataanalisis.entity.News;
import com.analis.dataanalisis.entity.WordsType;
import com.analis.dataanalisis.service.NewsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class NewsApiService {
    private static final Logger log = LoggerFactory.getLogger(NewsApiService.class);
    private static final String URL_CATEGORY = "http://localhost:8080/news/ids";
    private static final String URL_WORD_TYPE = "http://localhost:8080/analyzed?word=%s";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final NewsService newsService;

    public NewsApiService(RestTemplate restTemplate, ObjectMapper objectMapper, NewsService newsService) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.newsService = newsService;
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public Set<NewsResponse> getNewsByCategory(Category category) {
        List<News> news = newsService.findNewsByCategory(category);

        ResponseEntity<String> responseEntity = sendRequest(news.stream().map(News::getNewsId).toList());
        return getNewsResponses(responseEntity);
    }

    public Set<NewsResponse> getNewsByIds(List<String> ids) {
        ResponseEntity<String> stringResponseEntity = sendRequest(ids);
        return getNewsResponses(stringResponseEntity);
    }


    private Set<NewsResponse> getNewsResponses(ResponseEntity<String> responseEntity) {
        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            try {
                return objectMapper.readValue(responseEntity.getBody(), new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Failed to retrieve news: " + responseEntity.getStatusCode());
        }
    }

    private ResponseEntity<String> sendRequest(List<String> ids) {
        HttpHeaders headers = getHttpHeaders();

        HttpEntity<List<String>> request = new HttpEntity<>(ids, headers);

        return restTemplate.exchange(NewsApiService.URL_CATEGORY, HttpMethod.POST, request, String.class);
    }

    public Set<NewsResponse> getNewsByWordsType(WordsType wordsType) {
        Set<AnalyzedNews> analyzedNews = getAnalyzedNews(wordsType);
        if (analyzedNews.isEmpty()) return Collections.emptySet();
        List<String> ids = analyzedNews.stream()
                .map(AnalyzedNews::getId)
                .toList();
        ResponseEntity<String> stringResponseEntity = sendRequest(ids);
        return getNewsResponses(stringResponseEntity);
    }

    public Set<AnalyzedNews> getAnalyzedNews(WordsType wordsType) {
        log.info("SENDING REQUEST TO {}", URL_WORD_TYPE.formatted(wordsType.getStringWords()));
        ResponseEntity<Set<AnalyzedNews>> analyzedNewsResponseEntity = restTemplate.exchange(URL_WORD_TYPE.formatted(wordsType.getStringWords()), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        if (!analyzedNewsResponseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("ERROR while sending request to {} status code: {}", URL_WORD_TYPE, analyzedNewsResponseEntity.getStatusCode().value());
            return Collections.emptySet();
        }
        return analyzedNewsResponseEntity.getBody();
    }


}
