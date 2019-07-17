package com.cict.offical.network.controller;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.SysRole;
import com.cict.offical.network.entity.SysUser;
import com.cict.offical.network.service.SysRoleService;
import com.cict.offical.network.service.SysUserService;
import com.cict.offical.network.utils.Result;

import io.swagger.annotations.Api;

@Controller
@RequestMapping(value = "/user")
@Api(value="用户管理",tags= {"用户管理"})
public class SysUserController {
   
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysRoleService roleService;
	
	@GetMapping(value = "/getAllUser")	
	public @ResponseBody Result<Page<SysUser>> getAllUser(@RequestParam(value="username",defaultValue="") String userName, HttpServletRequest request) {
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");		
		int curPage = Integer.parseInt(cur);
        int pageSize = Integer.parseInt(size);
        
        Sort sort = new Sort(Sort.Direction.DESC, "id");//指定排序字段
        Pageable pageRequest = new PageRequest(curPage-1, pageSize,sort);
        Page<SysUser> pageData = userService.getAllUser("%"+userName+"%",pageRequest);
		return Result.returnResult(pageData); 
	}
	
	@GetMapping(value = "/getUser/{id}")	
	public @ResponseBody Result<SysUser> getUser(@PathVariable("id") int id) {
		SysUser user = userService.getUser(id);
		if(user!=null&&user.getRoles()!=null) {
			List<Integer> roleIds = user.getRoles().stream().map(SysRole::getId).collect(Collectors.toList());
			user.setRoleIds(roleIds);
		}
		return Result.returnResult(user);
	}
	
	@PostMapping(value = "/saveUser")	
	public @ResponseBody Result<SysUser> saveUser(@RequestBody SysUser user) {
		List<Integer> ids = user.getRoleIds();
        if(ids!=null) {
			List<SysRole> roleList = roleService.getRoleList(ids);
			user.setRoles(new HashSet<>(roleList));
		}
        
		SysUser result = userService.saveUser(user);
		return Result.returnResult(result);
	}	
	
	@PostMapping(value = "/deleteUser/{id}")	
	public @ResponseBody Result<String> deleteUser(@PathVariable("id") int id) {		
		userService.deleteUser(id);
		return Result.returnResult();
	}
	
}
