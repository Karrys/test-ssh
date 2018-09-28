package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.SysRoleRepository;
import com.cict.offical.network.entity.SysRole;
import com.cict.offical.network.utils.Result;

@Service
public class SysRoleService {
	@Autowired
	SysRoleRepository roleRepository;

	public Result<List<SysRole>> getAllRole() {
		return Result.returnResult(roleRepository.findAll());
	}

	public Result<String> addRole(SysRole role) {
		roleRepository.save(role);
		return Result.returnResult();		
	}

	public Result<String> updateRole(SysRole role) {
		roleRepository.save(role);
		return Result.returnResult();		
	}

	public Result<String> deleteRole(Integer id) {
		roleRepository.delete(id);
		return Result.returnResult();		
	}
}
