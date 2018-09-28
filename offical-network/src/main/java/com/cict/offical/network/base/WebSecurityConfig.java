package com.cict.offical.network.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cict.offical.network.service.SysUserService;

@Configuration
@EnableWebSecurity // 注解开启Spring Security的功能
// WebSecurityConfigurerAdapter:重写它的方法来设置一些web的安全细节
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${matcherPath}")
	private String matcherPaths;
	
	@Autowired
	private SysUserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		 
		http.authorizeRequests() // 定义哪些url需要保护，哪些url不需要保护
				.antMatchers(this.getAntMatchers(this.matcherPaths)).permitAll() // 定义不需要认证就可以访问
				.anyRequest().authenticated()
		        .and()
		     .formLogin().loginPage("/login")// 定义当需要用户登录时候，转到的登录页面
				.defaultSuccessUrl("/index")// 设置默认登录成功跳转页面
				.failureUrl("/login?error").permitAll() 
				.and()
				.logout().permitAll();
		http.csrf().disable();
		  
		

	}
	
	//定义不需要认证就可以访问
	private String[] getAntMatchers(String matcherPaths) {
		List<String> matcherList = new ArrayList<String>();
		matcherList.add("/login");
		if (StringUtils.isNotBlank(matcherPaths)) {
			String[] matcherArray = matcherPaths.split(",");
			for (String matcher : matcherArray) {
				String str = StringUtils.trimToEmpty(matcher);
				if (StringUtils.isNotBlank(str)) {
					matcherList.add(str);
				}
			}
		}
		String[] matchers = new String[matcherList.size()];
		matcherList.toArray(matchers);
		return matchers;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);
	}
}
