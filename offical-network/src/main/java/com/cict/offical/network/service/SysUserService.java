package com.cict.offical.network.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	    
		public Page<SysUser> getAllUser(String userName,Pageable pageable) {
			return userRepository.findByUsernameLike(userName,pageable);
		}
		public SysUser getUser(int id) {
			return userRepository.findOne(id);
		}
		
		public SysUser saveUser(SysUser user) {
			if(user.getId() == 0) {
			  String password = user.getPassword();
			  password = new BCryptPasswordEncoder().encode(password==null?"123456":password);
			  user.setPassword(password);
			}
			
			return userRepository.save(user);
		}
		public void deleteUser(Integer id) {
			userRepository.delete(id);	
		}
	}

	