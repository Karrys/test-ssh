package com.cict.offical.network.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cict.offical.network.entity.SysMenu;
import com.cict.offical.network.entity.SysRole;
import com.cict.offical.network.entity.SysUser;
import com.cict.offical.network.service.SysMenuService;
import com.cict.offical.network.service.SysRoleService;
import com.cict.offical.network.service.SysUserService;
import com.cict.offical.network.utils.R;
import com.cict.offical.network.utils.Result;
import com.cict.offical.network.utils.SpringSecurityUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value="登录",tags={"登录"})
public class LoginController {	
	
	@Autowired
	private SysUserService userService;	
	
	@Autowired
	private SysRoleService roleService;
	
	@Autowired
	private SysMenuService menuService;
	
	
	/*@GetMapping("/index")
	@ApiOperation(value = "首页",notes="首页")
	public String index(Model model) {
		model.addAttribute("title", "测试标题");
		model.addAttribute("content", "测试内容");
		model.addAttribute("extraInfo", "额外信息，只对管理员显示");
		//model.addAttribute("users",userService.getAllUser());
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
	}*/
	
	//获取当前用户的所有菜单
	@GetMapping(value="/getMenus")
	@ResponseBody
	@ApiOperation(value = "getMenus",notes="获取当前用户的所有菜单")
	public Result<List<Map<String, Object>>> getMenus() {
		List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
		List<SysMenu> menuList = new ArrayList<SysMenu>();
		
		SysUser currentUser = SpringSecurityUtil.getCurrentUser();
		
		if(R.ADMIN.equals(currentUser.getUsername().toUpperCase())) {
			menuList = menuService.getAllMenu();
		}else {
			Set<SysRole> roles = currentUser.getRoles();
			for(SysRole role : roles) {
				menuList.addAll(role.getMenus());
			}
		}
		
		//得到去重的菜单集合
		List<SysMenu> distinctList = menuList.stream().collect(
				Collectors.collectingAndThen( Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(SysMenu::getId))), ArrayList::new)
        );
		resultMap = getMenuListByParentId(distinctList,0);
	    return Result.returnResult(resultMap);
	}


	private List<Map<String, Object>> getMenuListByParentId(List<SysMenu> distinctList,int parentId) {		
		List<SysMenu> menus = distinctList.stream().filter(e->e.getParentId()==parentId).sorted(Comparator.comparing(SysMenu::getSort)).collect(Collectors.toList());
		List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
		
		for(SysMenu menu :menus) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("entity", menu);
			int pid = menu.getId();		
			List<Map<String, Object>> childs = getMenuListByParentId(distinctList,pid);
			map.put("childs",childs!=null&&childs.size()>0?childs:null);
			resultMap.add(map);			
		}
		return resultMap;
	}
}
