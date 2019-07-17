package com.cict.offical.network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.NewsRepository;
import com.cict.offical.network.entity.News;

@Service
public class NewsService {
	    @Autowired
	    NewsRepository newsRepository;
	    
		public Page<News> getAllNews(Pageable pageable) {
			return newsRepository.findAll(pageable);
		}
		
		public News getNews(int id) {
			return newsRepository.findOne(id);
		}
		
		public News saveNews(News news) {
			return newsRepository.save(news);
		}
		
		public void deleteNews(Integer id) {
			newsRepository.delete(id);
		}
	}

	