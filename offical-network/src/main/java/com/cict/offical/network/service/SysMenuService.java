package com.cict.offical.network.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.SysMenuRepository;
import com.cict.offical.network.entity.SysMenu;
import com.cict.offical.network.utils.SpringSecurityUtil;

@Service
public class SysMenuService {
	@Autowired
	SysMenuRepository menuRepository;

	public Page<SysMenu> getAllMenu(String name,Pageable pageable) {
		return menuRepository.findByNameLike(name,pageable);
	}
	
	public SysMenu getMenu(int id) {
		return menuRepository.findOne(id);
	}
	
	public SysMenu saveMenu(SysMenu menu) {
		String currentUser = SpringSecurityUtil.getCurrentUserName();
		menu.setLastUpdatedUser(currentUser);
		menu.setLastUpadatedTime(new Date());
		return menuRepository.save(menu);		
	}

	public void deleteMenu(Integer id) {
		menuRepository.delete(id);		
	}

	public List<SysMenu> getAllMenu() {
		return menuRepository.findAll();
	}
}
