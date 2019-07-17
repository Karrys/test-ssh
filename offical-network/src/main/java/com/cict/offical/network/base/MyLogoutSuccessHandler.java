package com.cict.offical.network.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cict.offical.network.utils.Result;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		logger.info("注销成功！");

		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(JSON.toJSONString(Result.returnResult("注销成功！")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {

		logger.info("注销。。。！");

		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(JSON.toJSONString(Result.returnErrorResult("注销ddd！")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
*/
	
	/*public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		logger.info("登录失败！");

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(Result.returnErrorResult("用户名或密码错误！")));

	}*/
}
