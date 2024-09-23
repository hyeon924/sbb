package com.example.sbb.article;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {
    @GetMapping("/article/list")
    public String list() {
        return "article_list";
    }
}
