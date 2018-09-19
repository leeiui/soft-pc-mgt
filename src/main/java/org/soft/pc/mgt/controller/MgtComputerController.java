package org.soft.pc.mgt.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.soft.pc.mgt.auth.AuthClass;
import org.soft.pc.mgt.auth.AuthMethod;
import org.soft.pc.mgt.common.SoftJsonUtil;
import org.soft.pc.mgt.common.UUIDUtil;
import org.soft.pc.mgt.model.MbgComputer;
import org.soft.pc.mgt.service.MgtComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
@AuthClass
@Controller
public class MgtComputerController {
	
	@Autowired
	private MgtComputerService mgtComputerService;
	@AuthMethod(roleId="1,2,3")
	@RequestMapping(path={"","/"}, method=RequestMethod.GET)
	public String goAdminView() {
		return "admin";
	}
	@AuthMethod(roleId="1,3")
	@RequestMapping(path="/computer/all", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getAllComputers(@RequestParam String pageSize, @RequestParam String pageNum) {
		return new ResponseEntity<String>(mgtComputerService.getAllComputers(pageSize, pageNum), HttpStatus.OK);
	}
	@AuthMethod(roleId="1,3")
	@RequestMapping(path="/computer/{cid}/delete", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> deleteById(@PathVariable String cid) {
		return new ResponseEntity<String>(mgtComputerService.deleteById(cid), HttpStatus.OK);
	}
	@AuthMethod(roleId="1,3")
	@RequestMapping(path="/computer/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> addComputer(@RequestParam(required=true) String tradeMark,
			@RequestParam(required=true) String price, @RequestParam("pic") 
			MultipartFile fileAttach, HttpServletRequest req) throws IOException {
		if(!fileAttach.isEmpty()) {
			String fileName = fileAttach.getOriginalFilename();
			
			String fileExt = FilenameUtils.getExtension(fileName);
			
			String newFileName = UUIDUtil.UUIDGenerator() + "." + fileExt;
			
			String realPath = req.getSession().getServletContext().getRealPath("/upload/pc_pic");
			
			File file = new File(realPath + "\\" + newFileName);
			
			FileUtils.copyInputStreamToFile(fileAttach.getInputStream(), file);
			
			MbgComputer mbgComputer = new MbgComputer();
			mbgComputer.setTrademark(tradeMark);
			mbgComputer.setPrice(Float.parseFloat(price));
			mbgComputer.setPic(newFileName);
			String result = mgtComputerService.addComputer(mbgComputer);
			
			return new ResponseEntity<String>(result, HttpStatus.OK);
		}
		Map<String, String> callback_json = new HashMap<String, String>();
		callback_json.put("msg", "图片没有上传");
		String result = SoftJsonUtil.objectToJson(callback_json);
		return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
	}
	@AuthMethod(roleId="1,3")
	@RequestMapping(path="/computer/{cid}/query", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> queryById(@PathVariable String cid) {
		return new ResponseEntity<String>(mgtComputerService.queryById(cid), HttpStatus.OK);
	}
	@AuthMethod(roleId="1,3")
	@RequestMapping(path="/computer/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> computerUpdate(@RequestParam(required=true) String cid, @RequestParam(required=true) String tradeMark,
			@RequestParam(required=true) String price) {
		MbgComputer mbgComputer = new MbgComputer();
		mbgComputer.setId(Integer.parseInt(cid));
		mbgComputer.setTrademark(tradeMark);
		mbgComputer.setPrice(Float.parseFloat(price));
		
		String result = mgtComputerService.computerUpdate(mbgComputer);
		System.out.println(result);
		return new ResponseEntity<String>(result, HttpStatus.OK);
		
	}

}
