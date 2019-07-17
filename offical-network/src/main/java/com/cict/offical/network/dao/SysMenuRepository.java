package com.cict.offical.network.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cict.offical.network.entity.SysMenu;

public interface SysMenuRepository extends JpaRepository<SysMenu, Integer> {
	//根据菜单名分页查询
    Page<SysMenu> findByNameLike(String name,Pageable pageable);
    
    //根据父菜单ID查询
    List<SysMenu> findByParentId(String pid);
}
