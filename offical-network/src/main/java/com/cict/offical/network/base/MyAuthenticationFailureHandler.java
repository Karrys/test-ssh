package com.cict.offical.network.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cict.offical.network.utils.Result;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		logger.info("登录失败！");

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(Result.returnErrorResult("用户名或密码错误！")));

	}
}
