package com.example.sbb.article;

import com.example.sbb.DataNotFoundException;
import com.example.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public void create(String title, String content, SiteUser user) {
        Article a = new Article();
        a.setTitle(title);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        a.setAuthor(user);
        this.articleRepository.save(a);
    }

    public Article getArticle(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if(article.isPresent()) {
            return article.get();
        } else {
            throw new DataNotFoundException("article not found");
        }
    }

    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        this.articleRepository.save(article);
    }
}
