package com.analis.dataanalisis.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "news", schema = "public")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private String newsId;

    public News() {
    }

    public News(Category category, String newsId) {
        this.category = category;
        this.newsId = newsId;
    }

    @Override
    public String toString() {

        return "News{" +
                "id=" + id +
                ", category=" + category +
                ", newsId='" + newsId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && category == news.category && Objects.equals(newsId, news.newsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, newsId);
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
