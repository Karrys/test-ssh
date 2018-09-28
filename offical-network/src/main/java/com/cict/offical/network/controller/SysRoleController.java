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
import com.cict.offical.network.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/role")
@Api(value="角色管理",tags= {"角色管理"})
public class SysRoleController {
   
	@Autowired
	private SysRoleService roleService;
	
	@GetMapping(value = "/getAllRole")	
	@ApiOperation(value = "查询所有的角色",notes="查询所有的角色")
	public @ResponseBody Result<List<SysRole>> getAllRole() {
		return roleService.getAllRole();
	}
	
	@PostMapping(value = "/addRole")
	@ApiOperation(value = "新增角色",notes="新增角色")
	public @ResponseBody Result<String> addRole(@RequestBody SysRole role) {
		return roleService.addRole(role);
	}
	
	@PostMapping(value = "/updateRole")	
	@ApiOperation(value = "修改角色",notes="修改角色")
	public @ResponseBody Result<String> updateRole(@RequestBody SysRole role) {
		return roleService.updateRole(role);
	}	
	
	@PostMapping(value = "/deleteRole")	
	@ApiOperation(value = "删除角色",notes="删除角色")
	public @ResponseBody Result<Object> deleteRole(Integer id) {
		return Result.returnResult();		
	}
	
}
