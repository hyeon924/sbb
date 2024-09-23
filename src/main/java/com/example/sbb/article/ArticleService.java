package com.example.sbb.article;

import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public void create(String title, String content) {
        Article a = new Article();
        a.setTitle(title);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(a);
    }
}
