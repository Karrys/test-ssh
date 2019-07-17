package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.ArticleRepository;
import com.cict.offical.network.entity.Article;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRepository;

	public Page<Article> getAllArticle(Specification<Article> spec, Pageable pageable) {
		return articleRepository.findAll(spec, pageable);
	}

	public Article getArticle(int id) {
		return articleRepository.findOne(id);
	}

	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}

	public void deleteArticle(Integer id) {
		articleRepository.delete(id);
	}
	
	public void deleteArticleBatch(List<Integer> ids) {
		//articleRepository.deleteArticleByIdIn(ids);
		articleRepository.deleteBatch(ids);
	}
}
