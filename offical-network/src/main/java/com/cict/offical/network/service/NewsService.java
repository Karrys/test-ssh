package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.NewsRepository;
import com.cict.offical.network.entity.News;

@Service
public class NewsService {
	    @Autowired
	    NewsRepository newsRepository;
	    
		public List<News> getAllNews() {
			return newsRepository.findAll();
		}
		public News addNews(News news) {
			newsRepository.save(news);
			return news;
		}
		public News updateNews(News news) {
			newsRepository.save(news);
			return news;
		}
		public void deleteNews(Integer id) {
			newsRepository.delete(id);
		}
	}

	