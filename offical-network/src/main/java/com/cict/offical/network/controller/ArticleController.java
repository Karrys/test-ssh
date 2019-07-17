package com.cict.offical.network.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.Article;
import com.cict.offical.network.service.ArticleService;
import com.cict.offical.network.utils.R;
import com.cict.offical.network.utils.R.ARTICLE_INFO;
import com.cict.offical.network.utils.Result;
import com.cict.offical.network.utils.SpringSecurityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/article")
@Api(value="文章管理",tags= {"文章管理"})
public class ArticleController {
   
	@Autowired
	private ArticleService articleService;
	
	@GetMapping(value = "/getAllArticle")	
	@ApiOperation(value = "查询我的文章列表",notes="查询我的文章列表")
	public @ResponseBody Result<Page<Article>> getAllArticle(HttpServletRequest request) {
		String title = request.getParameter("title");
		String state = request.getParameter("state");
		String createDateFrom = request.getParameter("createDateFrom");
		String createDateTo = request.getParameter("createDateTo");
		String releaseDateFrom = request.getParameter("releaseDateFrom");
		String releaseDateTo = request.getParameter("releaseDateTo");		
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");
		int curPage = Integer.parseInt(cur);
        int pageSize = Integer.parseInt(size);
        
        Pageable pageRequest = new PageRequest(curPage-1, pageSize);
        
		Specification<Article> spec = new Specification<Article>() {

			@Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				//like  文章标题
				if(StringUtils.isNotBlank(title)) {
					list.add(cb.like(root.get("title").as(String.class), "%" + title + "%"));
				}
				//文章状态
				if(StringUtils.isNotBlank(state)) {
					list.add(cb.equal(root.get("state").as(Integer.class), Integer.valueOf(state)));
				}
				//创建日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				if(StringUtils.isNotBlank(createDateFrom)) {
					try {
						list.add(cb.greaterThanOrEqualTo(root.get("createDate").as(Date.class), sdf.parse(createDateFrom)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(createDateTo)) {
					try {
						list.add(cb.lessThanOrEqualTo(root.get("createDate").as(Date.class), sdf.parse(createDateTo)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//发布日期
				
				if(StringUtils.isNotBlank(releaseDateFrom)) {
					try {
						list.add(cb.greaterThanOrEqualTo(root.get("releaseDate").as(Date.class), sdf.parse(releaseDateFrom)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(releaseDateTo)) {
					try {
						list.add(cb.lessThanOrEqualTo(root.get("releaseDate").as(Date.class), sdf.parse(releaseDateTo)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		        
		        //ORDER BY state ASC,releaseDate DESC
		        Predicate[] p = new Predicate[list.size()];
		        query.where(cb.and(list.toArray(p)));
		        //query.orderBy(cb.asc(root.get("state")),cb.desc(root.get("releaseDate")));

		        return query.getRestriction();
			}
		};
		
		Page<Article> pageData = articleService.getAllArticle(spec ,pageRequest);
		return Result.returnResult(pageData);
	}
	
	@GetMapping(value = "/getAllArticleAudit")	
	@ApiOperation(value = "查询审核文章列表",notes="查询审核文章列表")
	public @ResponseBody Result<Page<Article>> getAllArticleAudit(HttpServletRequest request) {
		String title = request.getParameter("title");
		String state = request.getParameter("state");
		String createDateFrom = request.getParameter("createDateFrom");
		String createDateTo = request.getParameter("createDateTo");
		String releaseDateFrom = request.getParameter("releaseDateFrom");
		String releaseDateTo = request.getParameter("releaseDateTo");		
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");
		int curPage = Integer.parseInt(cur);
        int pageSize = Integer.parseInt(size);
        
        Pageable pageRequest = new PageRequest(curPage-1, pageSize);
        
		Specification<Article> spec = new Specification<Article>() {

			@Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				//like  文章标题
				if(StringUtils.isNotBlank(title)) {
					list.add(cb.like(root.get("title").as(String.class), "%" + title + "%"));
				}
				//文章状态
				if(StringUtils.isNotBlank(state)) {
					list.add(cb.equal(root.get("state").as(Integer.class), Integer.valueOf(state)));
				}
				//创建日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				if(StringUtils.isNotBlank(createDateFrom)) {
					try {
						list.add(cb.greaterThanOrEqualTo(root.get("createDate").as(Date.class), sdf.parse(createDateFrom)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(createDateTo)) {
					try {
						list.add(cb.lessThanOrEqualTo(root.get("createDate").as(Date.class), sdf.parse(createDateTo)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//发布日期
				
				if(StringUtils.isNotBlank(releaseDateFrom)) {
					try {
						list.add(cb.greaterThanOrEqualTo(root.get("releaseDate").as(Date.class), sdf.parse(releaseDateFrom)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(StringUtils.isNotBlank(releaseDateTo)) {
					try {
						list.add(cb.lessThanOrEqualTo(root.get("releaseDate").as(Date.class), sdf.parse(releaseDateTo)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		        
		        //ORDER BY state ASC,releaseDate DESC
		        Predicate[] p = new Predicate[list.size()];
		        query.where(cb.and(list.toArray(p)));
		        //query.orderBy(cb.asc(root.get("state")),cb.desc(root.get("releaseDate")));

		        return query.getRestriction();
			}
		};
		
		Page<Article> pageData = articleService.getAllArticle(spec ,pageRequest);
		return Result.returnResult(pageData);
	}
	
	@GetMapping(value = "/getAllArticleDraft")	
	@ApiOperation(value = "查询草稿箱列表",notes="查询草稿箱列表")
	public @ResponseBody Result<Page<Article>> getAllArticleDraft(HttpServletRequest request) {		
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");
		int curPage = Integer.parseInt(cur);
        int pageSize = Integer.parseInt(size);
        
        Pageable pageRequest = new PageRequest(curPage-1, pageSize);
        
		Specification<Article> spec = new Specification<Article>() {

			@Override
			public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				//草稿状态
				list.add(cb.equal(root.get("state").as(Integer.class), ARTICLE_INFO.STATE.DRAFT));				
		        
		        Predicate[] p = new Predicate[list.size()];
		        query.where(cb.and(list.toArray(p)));
		        //按照创建日期倒序排列
		        query.orderBy(cb.desc(root.get("createDate")));

		        return query.getRestriction();
			}
		};
		
		Page<Article> pageData = articleService.getAllArticle(spec ,pageRequest);
		return Result.returnResult(pageData);
	}
	
	@GetMapping(value = "/getArticle/{id}")	
	@ApiOperation(value = "查询文章",notes="查询文章")
	public @ResponseBody Result<Article> getArticle(@PathVariable("id") int id) {		
		Article article = articleService.getArticle(id);		
		return Result.returnResult(article);
	}
	
	@PostMapping(value = "/saveArticle")	
	@ApiOperation(value = "保存文章",notes="保存文章")
	public @ResponseBody Result<Article> saveArticle(@RequestBody Article article) {
		article.setState(R.ARTICLE_INFO.STATE.DRAFT);
		article.setCreateDate(new Date());
		article.setCreateUser(SpringSecurityUtil.getCurrentUserName());
		Article result = articleService.saveArticle(article);
		return Result.returnResult(result);
	}
	
	@PostMapping(value = "/submitArticle")	
	@ApiOperation(value = "提交文章",notes="提交文章")
	public @ResponseBody Result<Article> submitArticle(@RequestBody Article article) {
		Date currentDate = new Date();
		article.setState(R.ARTICLE_INFO.STATE.TO_BE_AUDITED);		
		article.setSubmitDate(currentDate);	
		article.setSubmitUser(SpringSecurityUtil.getCurrentUserName());	
		//新增时直接提交的话，保存创建时间、创建人
        if(article.getCreateDate()==null) {
        	article.setCreateDate(currentDate);
		}
		if(article.getCreateUser()==null) {
			article.setCreateUser(SpringSecurityUtil.getCurrentUserName());
		}
		Article result = articleService.saveArticle(article);
		return Result.returnResult(result);
	}
	
	@PostMapping(value = "/auditArticle/{state}")	
	@ApiOperation(value = "审核文章",notes="审核文章")
	public @ResponseBody Result<Article> auditArticle(@RequestBody Article article,@PathVariable("state") int state) {
		
		if(state==R.ARTICLE_INFO.STATE.AUDIT_PASS) {
			article.setState(R.ARTICLE_INFO.STATE.AUDIT_PASS);
		}else if(state==R.ARTICLE_INFO.STATE.AUDIT_REJECTED){
			article.setState(R.ARTICLE_INFO.STATE.AUDIT_REJECTED);
			//article.setRejectReason(rejectReason);
		}
		article.setAuditDate(new Date());
		article.setAuditUser(SpringSecurityUtil.getCurrentUserName());
		
		Article result = articleService.saveArticle(article);
		return Result.returnResult(result);
	}
	
	@PostMapping(value = "/releaseArticle")	
	@ApiOperation(value = "发布文章",notes="发布文章")
	public @ResponseBody Result<Article> releaseArticle(@RequestBody Article article) {	
		//发布时  修改文章状态为已发布，发布日期为当前日期
		article.setState(R.ARTICLE_INFO.STATE.RELEASED);
		article.setReleaseDate(new Date());
		
		Article result = articleService.saveArticle(article);
		return Result.returnResult(result);
	}
	
	@PostMapping(value = "/offlineArticle/{id}")	
	@ApiOperation(value = "下线文章",notes="下线文章")
	public @ResponseBody Result<Article> offlineArticle(@PathVariable("id") int id) {
		//下线时  修改文章状态为已下线
		Article article = articleService.getArticle(id);
		article.setState(R.ARTICLE_INFO.STATE.OFFLINE);
		Article result = articleService.saveArticle(article);
		return Result.returnResult(result);
	}
	
	@PostMapping(value = "/deleteArticle/{id}")	
	@ApiOperation(value = "删除文章",notes="删除文章")
	public @ResponseBody Result<String> deleteArticle(@PathVariable("id") int id) {
		articleService.deleteArticle(id);
		return Result.returnResult();
	}
	
	@PostMapping(value = "/deleteArticleBatch")	
	@ApiOperation(value = "批量删除文章",notes="批量删除文章")
	public @ResponseBody Result<String> deleteArticleBatch(@RequestBody List<Integer> ids) {
		articleService.deleteArticleBatch(ids);
		return Result.returnResult();
	}
	
}
