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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/news")
@Api(value="新闻发布管理",tags= {"新闻发布息管理"})
public class NewsController {
   
	@Autowired
	private NewsService newsService;
	
	@GetMapping(value = "/getAllNews")	
	@ApiOperation(value = "查询所有的新闻",notes="查询所有的新闻")
	public @ResponseBody List<News> getAllNews() {
		return newsService.getAllNews();
	}
	
	@PostMapping(value = "/addNews")	
	@ApiOperation(value = "新增新闻",notes="新增新闻")
	public @ResponseBody News addNews(@RequestBody News news) {
		return newsService.addNews(news);
	}
	
	@PostMapping(value = "/updateNews")	
	@ApiOperation(value = "修改新闻",notes="修改新闻")
	public @ResponseBody News updateNews(@RequestBody News news) {
		return newsService.updateNews(news);
	}	
	
	@PostMapping(value = "/deleteNews")
	@ApiOperation(value = "删除新闻",notes="删除新闻")
	public @ResponseBody String deleteNews(Integer id) {
		newsService.deleteNews(id);
		return "";
	}
	
}
