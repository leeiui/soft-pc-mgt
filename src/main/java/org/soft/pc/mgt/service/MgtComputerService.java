package org.soft.pc.mgt.service;

import java.util.HashMap;
import java.util.Map;

import org.soft.pc.mgt.common.SoftHttpClientUtils;
import org.soft.pc.mgt.common.SoftJsonUtil;
import org.soft.pc.mgt.model.MbgComputer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MgtComputerService {
	
	@Value("${pc.core.base.url}")
	private String PC_CORE_BASE_URL;
	@Value("${pc.core.context-path}")
	private String PC_CORE_CONTEXT_PATH;
	
	public String getAllComputers(String pageSize, String pageNum) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageSize", pageSize);
		params.put("pageNum", pageNum);
		return SoftHttpClientUtils.doGet(PC_CORE_BASE_URL + PC_CORE_CONTEXT_PATH + "/computer/all", params);
	}

	public String deleteById(String cid) {
		return SoftHttpClientUtils.doGet(
				PC_CORE_BASE_URL + PC_CORE_CONTEXT_PATH + "/computer/" + cid + "/delete");
	}

	public String addComputer(MbgComputer mbgComputer) {

		String pcJson = SoftJsonUtil.objectToJson(mbgComputer);
		
		return SoftHttpClientUtils.doPostJson(PC_CORE_BASE_URL + PC_CORE_CONTEXT_PATH + "/computer/add", pcJson);
		
	}

	public String queryById(String cid) {

		return SoftHttpClientUtils.doGet(PC_CORE_BASE_URL + PC_CORE_CONTEXT_PATH + "/computer/" + cid + "/query");
	}

	public String computerUpdate(MbgComputer mbgComputer) {

		String pcJson = SoftJsonUtil.objectToJson(mbgComputer);
		System.out.println(pcJson);
		
		return SoftHttpClientUtils.doPostJson(PC_CORE_BASE_URL + PC_CORE_CONTEXT_PATH +"/computer/update", pcJson);
	}

}
