package org.example.service.impl;

import jakarta.annotation.Resource;
import org.example.entity.Article;
import org.example.repository.ArticleRepository;
import org.example.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;

    @Override
    public Article saveArticle(Article article) {
        articleRepository.save(article);
        return article;
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);

    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);

    }

    @Override
    public Article getArticle(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.orElse(null);
    }

    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }
}
