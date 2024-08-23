package com.analis.dataanalisis.controller;

import com.analis.dataanalisis.api.NewsApiService;
import com.analis.dataanalisis.controller.dto.NewsDTO;
import com.analis.dataanalisis.controller.dto.NewsResponse;
import com.analis.dataanalisis.entity.AnalyzedNews;
import com.analis.dataanalisis.entity.Category;
import com.analis.dataanalisis.entity.News;
import com.analis.dataanalisis.entity.WordsType;
import com.analis.dataanalisis.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class NewsController {
    private final NewsService newsService;
    private final NewsApiService newsApiService;

    public NewsController(NewsService newsService, NewsApiService newsApiService) {
        this.newsService = newsService;
        this.newsApiService = newsApiService;
    }

    @PostMapping("/")
    public ResponseEntity<?> getNews(@RequestBody Set<NewsDTO> newsDTOS) {
        Set<News> news = newsDTOS.stream()
                .map(newsDTO -> new News(Category.fromString(newsDTO.category()), newsDTO.newsId()))
                .collect(Collectors.toSet());
        newsService.saveAll(news);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/news")
    public Set<NewsResponse> getNews(@RequestParam Category category) {
        System.out.println(category.getDisplayName());
        return newsApiService.getNewsByCategory(category);
    }

    @GetMapping("/analyzed")
    public Set<NewsResponse> getAnalyzedNews(@RequestParam("word") WordsType wordsType) {
        return newsApiService.getNewsByWordsType(wordsType);
    }

    @GetMapping("/filter")
    public Set<String> getFilter(@RequestParam("word") WordsType wordsType) {
        Set<AnalyzedNews> analyzedNews = newsApiService.getAnalyzedNews(wordsType);
        return analyzedNews
                .stream()
                .filter(it -> it.getCategories().containsKey(wordsType))
                .flatMap(it -> it.getCategories().get(wordsType).stream())
                .collect(Collectors.toSet());
    }

    @GetMapping("/result")
    public Set<NewsResponse> getNewsByResultFilter(@RequestParam("word") WordsType wordsType,
                                                   @RequestParam("filter") String filter){
        List<String> ids = newsApiService.getAnalyzedNews(wordsType)
                .parallelStream()
                .filter(it -> it.getCategories().containsKey(wordsType) && it.getCategories().get(wordsType).contains(filter))
                .map(AnalyzedNews::getId)
                .toList();
        return newsApiService.getNewsByIds(ids);
    }
}
