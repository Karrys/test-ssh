package com.cict.offical.network.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.Recruit;
import com.cict.offical.network.service.RecruitService;

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
	public @ResponseBody List<Recruit> getAllRecruit() {
		return recruitService.getAllRecruit();
	}
	
	@PostMapping(value = "/addRecruit")	
	@ApiOperation(value = "新增招聘信息",notes="新增招聘信息")
	public @ResponseBody Recruit addRecruit(@RequestBody Recruit recruit) {
		return recruitService.addRecruit(recruit);
	}
	
	@PostMapping(value = "/updateRecruit")
	@ApiOperation(value = "修改招聘信息",notes="修改招聘信息")
	public @ResponseBody Recruit updateRecruit(@RequestBody Recruit recruit) {
		return recruitService.updateRecruit(recruit);
	}	
	
	@PostMapping(value = "/deleteRecruit")	
	@ApiOperation(value = "删除招聘信息",notes="删除招聘信息闻")
	public @ResponseBody String deleteRecruit(Integer id) {
		recruitService.deleteRecruit(id);
		return "";
	}
	
}
