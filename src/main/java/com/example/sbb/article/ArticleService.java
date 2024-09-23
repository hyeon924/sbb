package com.example.sbb.article;

import com.example.sbb.DataNotFoundException;
import com.example.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }

    private Specification<Article> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Article> a, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Article, SiteUser> u1 = a.join("author", JoinType.LEFT);
                return cb.or(
                        cb.like(a.get("title"), "%" + kw + "%"),
                        cb.like(a.get("content"), "%" + kw + "%")
                );
            }
        };
    }
    public  List<Article> getList(String kw) {
        Specification<Article> spec = search(kw);
        return this.articleRepository.findAll(spec);
    }
}
