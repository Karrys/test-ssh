package com.cict.offical.network.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cict.offical.network.entity.SysUser;

public class SpringSecurityUtil {
    public static SysUser getCurrentUser() {
    	SecurityContext securityContext = SecurityContextHolder.getContext();
    	Authentication authentication = securityContext.getAuthentication();
    	SysUser user = (SysUser) authentication.getPrincipal();
    	return user;    	
    }
    
    public static String getCurrentUserName() {
    	return getCurrentUser().getUsername();    	
    }
}
