package com.cict.offical.network.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.News;
import com.cict.offical.network.service.NewsService;
import com.cict.offical.network.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/news")
@Api(value="新闻发布管理",tags= {"新闻发布息管理"})
public class NewsController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
   
	@Autowired
	private NewsService newsService;
	
	@GetMapping(value = "/getAllNews")	
	@ApiOperation(value = "查询所有的新闻",notes="查询所有的新闻")
	public @ResponseBody Result<Page<News>> getAllNews(HttpServletRequest request) {
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");
		int curPage = Integer.parseInt(cur);
        int pageSize = Integer.parseInt(size);
        Sort sort = new Sort(Sort.Direction.DESC, "id");//指定排序字段
        Pageable pageRequest = new PageRequest(curPage-1, pageSize,sort);
        
        Page<News> pageData = newsService.getAllNews(pageRequest);
		return Result.returnResult(pageData);
	}
	
	@GetMapping(value = "/getNews/{id}")	
	@ApiOperation(value = "查询单条新闻",notes="查询单条新闻")
	public @ResponseBody Result<News> getNews(@PathVariable("id") int id) {
		News news = newsService.getNews(id);
		return Result.returnResult(news);
	}
	
	@PostMapping(value = "/saveNews")	
	@ApiOperation(value = "新增/修改新闻",notes="新增/修改新闻")
	public @ResponseBody Result<News> addNews(@RequestBody News news) {
		return Result.returnResult(newsService.saveNews(news));
	}
	
	@PostMapping(value = "/deleteNews/{id}")
	@ApiOperation(value = "删除新闻",notes="删除新闻")
	public @ResponseBody Result<String> deleteNews(@PathVariable("id") int id) {
		newsService.deleteNews(id);
		return Result.returnResult();
	}
}
