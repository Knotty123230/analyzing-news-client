package com.analis.dataanalisis.repository;

import com.analis.dataanalisis.entity.Category;
import com.analis.dataanalisis.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByCategory(Category category);
}
