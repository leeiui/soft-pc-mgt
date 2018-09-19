package org.soft.pc.mgt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soft.pc.mgt.auth.AuthClass;
import org.soft.pc.mgt.auth.AuthMethod;
import org.soft.pc.mgt.common.CookieUtils;
import org.soft.pc.mgt.model.MbgUser;
import org.soft.pc.mgt.service.MgtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.util.StringUtils;

@AuthClass
@Controller
public class UserController {

	@Autowired
	private MgtUserService mgtUserService;
	
	@Value("${COOKIE_AUTH_KEY}")
	private String COOKIE_AUTH_KEY;
	
	@AuthMethod(roleId="1,2,3")
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String goLoginView() {
		return "login";
	}
	
	@AuthMethod(roleId="1,2,3")
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String passwd, HttpServletRequest request, HttpServletResponse response) {
		
		String token = mgtUserService.login(username, passwd);
		if(!StringUtils.isEmpty(token)) {
			CookieUtils.setCookie(request, response, COOKIE_AUTH_KEY, token);
			return "redirect:/";
		}
		return "redirect:/login";
		
	}
	@AuthMethod(roleId="1,2")
	@RequestMapping(path="/user/update", method=RequestMethod.POST)
	public ResponseEntity<String> updateUser(String uid, String username, String state, String roleId) {
		MbgUser user = new MbgUser();
		user.setId(Integer.parseInt(uid));
		user.setUsername(username);
		user.setRoleId(Integer.parseInt(roleId));
		user.setState(state);
		String result = mgtUserService.updateUser(user);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	@AuthMethod(roleId="1,2,3")
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String cookieValue = CookieUtils.getCookieValue(request, COOKIE_AUTH_KEY);
		if(!StringUtils.isEmpty(cookieValue)) {
			CookieUtils.deleteCookie(request, response, COOKIE_AUTH_KEY);
		}
		mgtUserService.logout(cookieValue);
		return "redirect:/login";
	}
}
