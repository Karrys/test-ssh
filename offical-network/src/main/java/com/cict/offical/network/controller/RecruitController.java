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

@Controller
@RequestMapping(value = "/recruit")
public class RecruitController {
   
	@Autowired
	private RecruitService recruitService;
	
	@GetMapping(value = "/getAllRecruit")	
	public @ResponseBody List<Recruit> getAllRecruit() {
		return recruitService.getAllRecruit();
	}
	
	@PostMapping(value = "/addRecruit")	
	public @ResponseBody Recruit addRecruit(@RequestBody Recruit recruit) {
		return recruitService.addRecruit(recruit);
	}
	
	@PostMapping(value = "/updateRecruit")	
	public @ResponseBody Recruit updateRecruit(@RequestBody Recruit recruit) {
		return recruitService.updateRecruit(recruit);
	}	
	
	@PostMapping(value = "/deleteRecruit")	
	public @ResponseBody String deleteRecruit(Integer id) {
		recruitService.deleteRecruit(id);
		return "";
	}
	
}
