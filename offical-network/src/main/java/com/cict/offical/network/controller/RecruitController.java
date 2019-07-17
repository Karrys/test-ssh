package com.cict.offical.network.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.Recruit;
import com.cict.offical.network.service.RecruitService;
import com.cict.offical.network.utils.R;
import com.cict.offical.network.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/recruit")
@Api(value="招聘信息管理",tags= {"招聘信息管理"})
public class RecruitController {
   
	@Autowired
	private RecruitService recruitService;
	
	@GetMapping(value = "/getAllRecruit")	
	@ApiOperation(value = "查询所有的招聘信息",notes="查询所有的招聘信息")
	public @ResponseBody Result<Page<Recruit>> getAllRecruit(HttpServletRequest request) {
		String postName = request.getParameter("postName");
		String state = request.getParameter("state");
		String releaseDateFrom = request.getParameter("releaseDateFrom");
		String releaseDateTo = request.getParameter("releaseDateTo");
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");
		int curPage = Integer.parseInt(cur);
        int pageSize = Integer.parseInt(size);
        
        Pageable pageRequest = new PageRequest(curPage-1, pageSize);
        
		Specification<Recruit> spec = new Specification<Recruit>() {

			@Override
			public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				//like  职位名称
				if(!StringUtils.isBlank(postName)) {
					list.add(cb.like(root.get("postName").as(String.class), "%" + postName + "%"));
				}
				//职位状态
				if(!StringUtils.isBlank(state)) {
					list.add(cb.equal(root.get("state").as(Integer.class), Integer.valueOf(state)));
				}
				//发布日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				if(!StringUtils.isBlank(releaseDateFrom)) {
					try {
						list.add(cb.greaterThanOrEqualTo(root.get("releaseDate").as(Date.class), sdf.parse(releaseDateFrom)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(!StringUtils.isBlank(releaseDateTo)) {
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
		        query.orderBy(cb.asc(root.get("state")),cb.desc(root.get("releaseDate")));

		        return query.getRestriction();
			}
		};
		
		Page<Recruit> pageData = recruitService.getAllRecruit(spec ,pageRequest);
		return Result.returnResult(pageData);
	}
	
	@GetMapping(value = "/getRecruit/{id}")	
	@ApiOperation(value = "查询单条招聘信息",notes="查询单条招聘信息")
	public @ResponseBody Result<Recruit> getRecruit(@PathVariable("id") int id) {		
		Recruit recruit = recruitService.getRecruit(id);
		if(recruit!=null&&recruit.getKeyWords()!=null) {
			List<String> list = Arrays.asList(recruit.getKeyWords().split(","));
			recruit.setKeyWordsList(list);
		}
		return Result.returnResult(recruit);
	}
	
	@PostMapping(value = "/saveRecruit")	
	@ApiOperation(value = "新增/修改招聘信息",notes="新增/修招聘信息")
	public @ResponseBody Result<Recruit> saveRecruit(@RequestBody Recruit recruit) {
		List<String> list = recruit.getKeyWordsList();
		recruit.setKeyWords(StringUtils.join(list.toArray(), ","));
		
		recruit.setState(R.RECRUIT_INFO.STATE.TO_BE_RELEASED);
		recruit.setSaveDate(new Date());
		
		Recruit result = recruitService.saveRecruit(recruit);
		return Result.returnResult(result);
	}
	
	@PostMapping(value = "/releaseRecruit")	
	@ApiOperation(value = "发布招聘信息",notes="发布招聘信息")
	public @ResponseBody Result<Recruit> releaseRecruit(@RequestBody Recruit recruit) {
		List<String> list = recruit.getKeyWordsList();
		recruit.setKeyWords(StringUtils.join(list.toArray(), ","));
		
		recruit.setState(R.RECRUIT_INFO.STATE.RELEASED);
		recruit.setReleaseDate(new Date());
		
		Recruit result = recruitService.saveRecruit(recruit);
		return Result.returnResult(result);
	}
	
	@PostMapping(value = "/offlineRecruit/{id}")	
	@ApiOperation(value = "下线招聘信息",notes="下线招聘信息")
	public @ResponseBody Result<Recruit> offlineRecruit(@PathVariable("id") int id) {
		Recruit recruit = recruitService.getRecruit(id);
		recruit.setState(R.RECRUIT_INFO.STATE.OFFLINE);
		recruit.setSaveDate(new Date());
		Recruit result = recruitService.saveRecruit(recruit);
		return Result.returnResult(result);
	}
	
	@PostMapping(value = "/deleteRecruit/{id}")	
	@ApiOperation(value = "删除招聘信息",notes="删除招聘信息")
	public @ResponseBody Result<String> deleteRecruit(@PathVariable("id") int id) {
		recruitService.deleteRecruit(id);
		return Result.returnResult();
	}
	
}
