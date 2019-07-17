package com.cict.offical.network.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cict.offical.network.entity.SysRole;

public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {
	//根据角色名分页查询
    Page<SysRole> findByNameLike(String name,Pageable pageable);   
    
    List<SysRole> findByIdIn(List<Integer> ids);
}
