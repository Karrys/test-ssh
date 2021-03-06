package com.cict.offical.network.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cict.offical.network.dao.SysUserRepository;
import com.cict.offical.network.entity.SysUser;

@Service
public class SysUserService implements UserDetailsService {
	    @Autowired
	    SysUserRepository userRepository;
	    @Override
	    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
	        SysUser user = userRepository.findByUsername(s);
	        if (user == null) {
	            throw new UsernameNotFoundException("用户名不存在");
	        }
	        System.out.println("s:"+s);
	        System.out.println("username:"+user.getUsername()+";password:"+user.getPassword());
	        System.out.println("roles:"+user.getAuthorities());
	        
	        return user;
	    }
		public List<SysUser> getAllUser() {
			return userRepository.findAll();
		}
		public SysUser addUser(SysUser user) {
			userRepository.save(user);
			return user;
		}
		public SysUser updateUser(SysUser user) {
			userRepository.save(user);
			return user;
		}
		public void deleteUser(Integer id) {
			userRepository.delete(id);			
		}
	}

	