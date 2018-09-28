package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.SysRoleRepository;
import com.cict.offical.network.entity.SysRole;

@Service
public class SysRoleService {
	@Autowired
	SysRoleRepository roleRepository;

	public List<SysRole> getAllRole() {
		return roleRepository.findAll();
	}

	public SysRole addRole(SysRole role) {
		roleRepository.save(role);
		return role;
	}

	public SysRole updateRole(SysRole role) {
		roleRepository.save(role);
		return role;
	}

	public void deleteRole(Integer id) {
		roleRepository.delete(id);
	}
}
