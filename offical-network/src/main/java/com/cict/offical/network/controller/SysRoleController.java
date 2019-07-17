package com.cict.offical.network.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.SysMenu;
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
	public @ResponseBody Result<Page<SysRole>> getAllRole(@RequestParam(value="name",defaultValue="") String name, HttpServletRequest request) {
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");		
		int curPage = Integer.parseInt(cur);
        int pageSize = Integer.parseInt(size);
        
        Pageable pageRequest = new PageRequest(curPage-1, pageSize);
        Page<SysRole> page = roleService.getAllRole("%"+name+"%",pageRequest);
		return Result.returnResult(page);
	}
	
	@GetMapping(value = "/getRole/{id}")
	@ApiOperation(value = "查询角色",notes="查询角色")
	public @ResponseBody Result<SysRole> getRole(@PathVariable("id") int id) {
		SysRole role = roleService.getRole(id);
		if(role!=null&&role.getMenus()!=null) {
			Set<SysMenu> menuList = role.getMenus();
			//前台菜单是级联的，这里只返回叶子菜单
			List<Integer> list = menuList.stream().filter(e->e.isLeaf()).map(SysMenu::getId).collect(Collectors.toList());			
			role.setMenuIds(list);
		}
		return Result.returnResult(role);
	}	
	
	@PostMapping(value = "/saveRole")	
	@ApiOperation(value = "新增/修改角色",notes="新增/修改角色")
	public @ResponseBody Result<String> saveRole(@RequestBody SysRole role) {
		roleService.saveRole(role);
		return Result.returnResult();
	}	
	
	@PostMapping(value = "/deleteRole/{id}")	
	@ApiOperation(value = "删除角色",notes="删除角色")
	public @ResponseBody Result<String> deleteRole(@PathVariable("id") int id) {		
		roleService.deleteRole(id);	
		return Result.returnResult();
	}
	
	@GetMapping(value = "/getRoles")
	@ApiOperation(value = "查询所有的角色",notes="查询所有的角色")
	public @ResponseBody  Result<List<SysRole>> getRoles() {
		List<SysRole> list = roleService.getAllRoleList();
		return Result.returnResult(list);
	}

}