package com.cict.offical.network.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.SysRole;
import com.cict.offical.network.service.SysRoleService;

@Controller
@RequestMapping(value = "/role")
public class SysRoleController {
   
	@Autowired
	private SysRoleService roleService;
	
	@GetMapping(value = "/getAllRole")	
	public @ResponseBody List<SysRole> getAllRole() {
		return roleService.getAllRole();
	}
	
	@PostMapping(value = "/addRole")	
	public @ResponseBody SysRole addRole(@RequestBody SysRole role) {
		return roleService.addRole(role);
	}
	
	@PostMapping(value = "/updateRole")	
	public @ResponseBody SysRole updateRole(@RequestBody SysRole role) {
		return roleService.updateRole(role);
	}	
	
	@PostMapping(value = "/deleteRole")	
	public @ResponseBody String deleteRole(Integer id) {
		roleService.deleteRole(id);
		return "";
	}
	
}
