package com.analis.dataanalisis.service;

import com.analis.dataanalisis.entity.Category;
import com.analis.dataanalisis.entity.News;
import com.analis.dataanalisis.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public void saveAll(Set<News> news) {
        newsRepository.saveAll(news);
    }

    public List<News> findNewsByCategory(Category category) {
        return newsRepository.findAllByCategory(category);
    }
}
