package com.cict.offical.network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cict.offical.network.service.SysUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="登录",tags={"登录"})
public class LoginController {	
	
	@Autowired
	private SysUserService userService;	
	
	
	@GetMapping("/index")
	@ApiOperation(value = "首页",notes="首页")
	public String index(Model model) {
		model.addAttribute("title", "测试标题");
		model.addAttribute("content", "测试内容");
		model.addAttribute("extraInfo", "额外信息，只对管理员显示");
		model.addAttribute("users",userService.getAllUser());
	    return "index";
	}
	 
	@GetMapping("/hello")
	@ApiOperation(value = "hello",notes="hello")
	public String hello() {
	    return "hello";
	}
	 
	@GetMapping("/login")
	@ApiOperation(value = "登录",notes="登录")
	public String login() {
	    return "login";
	}


}
