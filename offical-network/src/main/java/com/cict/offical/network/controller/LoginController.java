package com.cict.offical.network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {	
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "测试标题");
		model.addAttribute("content", "测试内容");
		model.addAttribute("extraInfo", "额外信息，只对管理员显示");
	    return "index";
	}
	 
	@RequestMapping("/hello")
	public String hello() {
	    return "hello";
	}
	 
	@RequestMapping("/login")
	public String login() {
	    return "login";
	}


}
