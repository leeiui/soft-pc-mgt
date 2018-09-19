package org.soft.pc.mgt.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soft.pc.mgt.auth.AuthUtil;
import org.soft.pc.mgt.common.CookieUtils;
import org.soft.pc.mgt.common.SoftJsonUtil;
import org.soft.pc.mgt.model.MbgComputer;
import org.soft.pc.mgt.model.MbgUser;
import org.soft.pc.mgt.service.MgtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.druid.util.StringUtils;

@Component
public class AuthIntercepter extends HandlerInterceptorAdapter{

	@Value("${COOKIE_AUTH_KEY}")
	private String COOKIE_AUTH_KEY;
	
	@Autowired
	private MgtUserService mgtUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = CookieUtils.getCookieValue(request, COOKIE_AUTH_KEY);
		if(StringUtils.isEmpty(token)) {
			response.sendRedirect("/mgt/login");
			return false;
		} else {
			String userJson = mgtUserService.getUserByToken(token);
			if(StringUtils.isEmpty(userJson)) {
				response.sendRedirect("/mgt/login");
				return false;
			}
			return authBasedOnRole(userJson, handler);
		}
	}

	private Boolean authBasedOnRole(String userJson, Object handler) {

		MbgUser user = SoftJsonUtil.jsonToPojo(userJson, MbgUser.class);
		Integer roleId = user.getRoleId();
		Set<String> actions = AuthUtil.allAuths.get(roleId.toString());
		HandlerMethod hm = (HandlerMethod) handler;
		String reqMethod = hm.getBean().getClass().getName() + "." + hm.getMethod().getName();
		if(!actions.isEmpty() && actions.contains(reqMethod)) {
			return true;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
	
}
