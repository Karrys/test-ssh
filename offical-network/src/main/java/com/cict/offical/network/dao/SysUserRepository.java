package com.cict.offical.network.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cict.offical.network.entity.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Integer> {
    SysUser findByUsername(String username);
}