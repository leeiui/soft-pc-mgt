package org.soft.pc.mgt.service;

import java.util.HashMap;
import java.util.Map;

import org.soft.pc.mgt.common.SoftHttpClientUtils;
import org.soft.pc.mgt.common.SoftJsonUtil;
import org.soft.pc.mgt.model.MbgUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MgtUserService {
	
	@Value("${auth.core.base.url}")
	private String AUTH_CORE_BASE_URL;
	@Value("${auth.core.context-path}")
	private String AUTH_CORE_CONTEXT_PATH;
	
	public String updateUser(MbgUser user) {
		String userJson = SoftJsonUtil.objectToJson(user);
		return SoftHttpClientUtils.doPostJson(AUTH_CORE_BASE_URL + AUTH_CORE_CONTEXT_PATH +"/user/update", userJson);
	}

	public String login(String username, String passwd) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("username", username);
		param.put("passwd", passwd);
		return SoftHttpClientUtils.doPost(AUTH_CORE_BASE_URL + AUTH_CORE_CONTEXT_PATH + "/user/login", param);
	}

	public String logout(String token) {
		return SoftHttpClientUtils.doGet(AUTH_CORE_BASE_URL + AUTH_CORE_CONTEXT_PATH + "/" + token + "/logout");
	}

	public String getUserByToken(String token) {
		return SoftHttpClientUtils.doGet(AUTH_CORE_BASE_URL + AUTH_CORE_CONTEXT_PATH + "/" + token + "/token");
	}

}
