package com.cict.offical.network.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.SysUser;
import com.cict.offical.network.service.SysUserService;

@Controller
@RequestMapping(value = "/user")	
public class SysUserController {
   
	@Autowired
	private SysUserService userService;
	
	@GetMapping(value = "/getAllUser")	
	public @ResponseBody List<SysUser> getAllUser() {
		return userService.getAllUser();
	}
	
	@PostMapping(value = "/addUser")	
	public @ResponseBody SysUser addUser(@RequestBody SysUser user) {
		return userService.addUser(user);
	}
	
	@PostMapping(value = "/updateUser")	
	public @ResponseBody SysUser updateUser(@RequestBody SysUser user) {
		return userService.updateUser(user);
	}	
	
	@PostMapping(value = "/deleteUser")	
	public @ResponseBody String deleteUser(Integer id) {
		userService.deleteUser(id);
		return "";
	}
	
}
