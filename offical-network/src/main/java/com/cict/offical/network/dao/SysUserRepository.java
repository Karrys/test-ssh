package com.cict.offical.network.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cict.offical.network.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {
	//分页查询
	Page<SysUser> findAll(Pageable pageable);
	
	//根据用户名分页查询
	Page<SysUser> findByUsernameLike(String username,Pageable pageable);
	
	//根据用户名查找用户信息
    SysUser findByUsername(String username);
}