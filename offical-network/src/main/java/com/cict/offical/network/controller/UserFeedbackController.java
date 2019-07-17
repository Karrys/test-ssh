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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.Recruit;
import com.cict.offical.network.entity.UserFeedback;
import com.cict.offical.network.service.UserFeedbackService;
import com.cict.offical.network.utils.R;
import com.cict.offical.network.utils.Result;
import com.cict.offical.network.utils.SpringSecurityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/feedback")
@Api(value="用户反馈管理",tags= {"用户反馈管理"})
public class UserFeedbackController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
   
	@Autowired
	private UserFeedbackService userFeedbackService;
	
	@GetMapping(value = "/getAllFeedback")	
	@ApiOperation(value = "查询所有的用户反馈",notes="查询所有的用户反馈")
	public @ResponseBody Result<Page<UserFeedback>> getAllFeedback(HttpServletRequest request) {
		String company = request.getParameter("company");
		String state = request.getParameter("state");
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");
		int curPage = Integer.parseInt(cur);
        int pageSize = Integer.parseInt(size);
      
        Pageable pageRequest = new PageRequest(curPage-1, pageSize);
        
       
		
		Specification<UserFeedback> spec = new Specification<UserFeedback>() {

			@Override
			public Predicate toPredicate(Root<UserFeedback> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				//like  公司名称
				if(!StringUtils.isBlank(company)) {
					list.add(cb.like(root.get("company").as(String.class), "%" + company + "%"));
				}
				//处理状态
				if(!StringUtils.isBlank(state)) {
					list.add(cb.equal(root.get("state").as(Integer.class), Integer.valueOf(state)));
				}
				//提交日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
				if(!StringUtils.isBlank(dateFrom)) {
					try {
						list.add(cb.greaterThanOrEqualTo(root.get("submitDate").as(Date.class), sdf.parse(dateFrom)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(!StringUtils.isBlank(dateTo)) {
					try {
						list.add(cb.lessThanOrEqualTo(root.get("submitDate").as(Date.class), sdf.parse(dateTo)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		        
		        //ORDER BY submitDate DESC
		        Predicate[] p = new Predicate[list.size()];
		        query.where(cb.and(list.toArray(p)));
		        query.orderBy(cb.desc(root.get("submitDate")));

		        return query.getRestriction();
			}
		};
		
		Page<UserFeedback> pageData = userFeedbackService.getAllUserFeedback(spec,pageRequest);
		return Result.returnResult(pageData);			
	}
	
	@GetMapping(value = "/getFeedback/{id}")	
	@ApiOperation(value = "查询单条用户反馈",notes="查询单条用户反馈")
	public @ResponseBody Result<UserFeedback> getFeedback(@PathVariable("id") int id) {
		UserFeedback userFeedback = userFeedbackService.getUserFeedback(id);
		return Result.returnResult(userFeedback);
	}
	
	@PostMapping(value = "/saveFeedback")	
	@ApiOperation(value = "新增/修改用户反馈",notes="新增/修改用户反馈")
	public @ResponseBody Result<UserFeedback> saveFeedback(@RequestBody UserFeedback userFeedback) {
		userFeedback.setSubmitDate(new Date());
		userFeedback.setState(R.USER_FEEDBACK.STATE.TO_DO);
		return Result.returnResult(userFeedbackService.saveUserFeedback(userFeedback));
	}
	
	@PostMapping(value = "/deleteFeedback/{id}")
	@ApiOperation(value = "删除用户反馈",notes="删除用户反馈")
	public @ResponseBody Result<String> deleteFeedback(@PathVariable("id") int id) {
		userFeedbackService.deleteUserFeedback(id);
		return Result.returnResult();
	}
	
	@PostMapping(value = "/deleteFeedbackBatch")
	@ApiOperation(value = "批量删除用户反馈",notes="批量删除用户反馈")
	public @ResponseBody Result<String> deleteFeedbackBatch(@RequestBody List<Integer> ids) {
		userFeedbackService.deleteUserFeedbackBatch(ids);
		return Result.returnResult();
	}
	
	@PostMapping(value = "/updateState/{id}/{state}")
	@ApiOperation(value = "更改用户反馈处理状态",notes="更改用户反馈处理状态")
	public @ResponseBody Result<String> updateState(@PathVariable("id") int id,@PathVariable("state") int state) {
		UserFeedback userFeedback = userFeedbackService.getUserFeedback(id);
		userFeedback.setState(state);
		userFeedback.setHandleUser(SpringSecurityUtil.getCurrentUserName());
		userFeedback.setHandleDate(new Date());		
		userFeedbackService.saveUserFeedback(userFeedback);			
		
		return Result.returnResult();
	}
}
