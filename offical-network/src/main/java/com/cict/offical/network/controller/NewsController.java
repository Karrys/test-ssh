package com.cict.offical.network.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.News;
import com.cict.offical.network.service.NewsService;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
   
	@Autowired
	private NewsService newsService;
	
	@GetMapping(value = "/getAllNews")	
	public @ResponseBody List<News> getAllNews() {
		return newsService.getAllNews();
	}
	
	@PostMapping(value = "/addNews")	
	public @ResponseBody News addNews(@RequestBody News news) {
		return newsService.addNews(news);
	}
	
	@PostMapping(value = "/updateNews")	
	public @ResponseBody News updateNews(@RequestBody News news) {
		return newsService.updateNews(news);
	}	
	
	@PostMapping(value = "/deleteNews")	
	public @ResponseBody String deleteNews(Integer id) {
		newsService.deleteNews(id);
		return "";
	}
	
}
