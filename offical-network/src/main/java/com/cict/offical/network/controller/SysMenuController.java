package com.cict.offical.network.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.cict.offical.network.service.SysMenuService;
import com.cict.offical.network.utils.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "/menu")
@Api(value = "菜单管理", tags = { "菜单管理" })
public class SysMenuController {

	@Autowired
	private SysMenuService menuService;

	@GetMapping(value = "/getAllMenu")
	@ApiOperation(value = "查询所有的菜单", notes = "查询所有的菜单")
	public @ResponseBody Result<Page<SysMenu>> getAllMenu(@RequestParam(value = "name", defaultValue = "") String name,
			HttpServletRequest request) {
		String cur = request.getParameter("curPage");
		String size = request.getParameter("pageSize");
		int curPage = Integer.parseInt(cur);
		int pageSize = Integer.parseInt(size);

		Pageable pageRequest = new PageRequest(curPage - 1, pageSize);
		Page<SysMenu> pageData = menuService.getAllMenu("%" + name + "%", pageRequest);
		return Result.returnResult(pageData);
	}

	@GetMapping(value = "/getMenu/{id}")
	public @ResponseBody Result<SysMenu> getMenu(@PathVariable("id") int id) {
		SysMenu menu = menuService.getMenu(id);
		return Result.returnResult(menu);
	}

	@PostMapping(value = "/saveMenu")
	@ApiOperation(value = "新增/修改菜单", notes = "新增/修改菜单")
	public @ResponseBody Result<String> saveMenu(@RequestBody SysMenu menu) {
		menuService.saveMenu(menu);
		return Result.returnResult();
	}

	@PostMapping(value = "/deleteMenu/{id}")
	@ApiOperation(value = "删除菜单", notes = "删除菜单")
	public @ResponseBody Result<String> deleteMenu(@PathVariable("id") int id) {
		menuService.deleteMenu(id);
		return Result.returnResult();
	}

	// 获取所有菜单 (给角色分配菜单)
	@GetMapping(value = "/getMenus")
	@ResponseBody
	@ApiOperation(value = "getMenus", notes = "获取所有菜单")
	public Result<List<Map<String, Object>>> getMenus() {
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();
		List<SysMenu> distinctList = menuService.getAllMenu();
		resultMap = getMenuListByParentId(distinctList, 0);
		return Result.returnResult(resultMap);
	}

	private List<Map<String, Object>> getMenuListByParentId(List<SysMenu> distinctList, int parentId) {
		List<SysMenu> menus = distinctList.stream().filter(e -> e.getParentId() == parentId)
				.sorted(Comparator.comparing(SysMenu::getSort)).collect(Collectors.toList());
		List<Map<String, Object>> resultMap = new ArrayList<Map<String, Object>>();

		for (SysMenu menu : menus) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", menu.getId());
			map.put("name", menu.getName());
			int pid = menu.getId();
			List<Map<String, Object>> childs = getMenuListByParentId(distinctList, pid);
			if (childs != null && childs.size() > 0) {
				map.put("childs", childs);
			}
			resultMap.add(map);
		}
		return resultMap;
	}
}
