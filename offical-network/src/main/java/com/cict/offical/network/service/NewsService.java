package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.NewsRepository;
import com.cict.offical.network.entity.News;
import com.cict.offical.network.utils.Result;

@Service
public class NewsService {
	    @Autowired
	    NewsRepository newsRepository;
	    
		public Result<List<News>> getAllNews() {
			return Result.returnResult(newsRepository.findAll());
		}
		public Result<String> addNews(News news) {
			newsRepository.save(news);
			return Result.returnResult();
		}
		public Result<String> updateNews(News news) {
			newsRepository.save(news);
			return Result.returnResult();
		}
		public Result<String> deleteNews(Integer id) {
			newsRepository.delete(id);
			return Result.returnResult();
		}
	}

	