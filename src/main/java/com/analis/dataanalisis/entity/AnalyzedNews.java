package com.analis.dataanalisis.entity;

import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AnalyzedNews {
    @Id
    private String id;
    private String title;
    private String content;
    private Instant analyzedAt;
    private Map<WordsType, Set<String>> categories;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<WordsType, Set<String>> getCategories() {
        return categories;
    }

    public void setCategories(Map<WordsType, Set<String>> categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(Instant analyzedAt) {
        this.analyzedAt = analyzedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AnalyzedNews{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", analyzedAt=" + analyzedAt +
                ", categories=" + categories +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalyzedNews that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(analyzedAt, that.analyzedAt) && Objects.equals(categories, that.categories) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, analyzedAt, categories, url);
    }
}
