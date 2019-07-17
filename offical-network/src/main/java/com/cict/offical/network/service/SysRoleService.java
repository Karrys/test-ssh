package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.SysRoleRepository;
import com.cict.offical.network.entity.SysRole;

@Service
public class SysRoleService {
	@Autowired
	SysRoleRepository roleRepository;

	public Page<SysRole> getAllRole(String name, Pageable pageable) {
		return roleRepository.findByNameLike(name,pageable);
	}

	public SysRole getRole(int id) {
		return roleRepository.findOne(id);
	}

	public SysRole saveRole(SysRole role) {
		return roleRepository.save(role);
	}

	public void deleteRole(int id) {
		roleRepository.delete(id);
	}
	
	public List<SysRole> getRoleList(List<Integer> ids){
		return roleRepository.findByIdIn(ids);
	}

	public List<SysRole> getAllRoleList() {		
		return roleRepository.findAll();
	}
}
