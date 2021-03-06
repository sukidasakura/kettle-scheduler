package com.zhaxd.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.dto.ResultDto;
import com.zhaxd.core.model.KUser;
import com.zhaxd.web.service.TransRecordService;
import com.zhaxd.web.utils.JsonUtils;

@RestController
@RequestMapping("/trans/record/")
public class TransRecordController {

	@Autowired
	private TransRecordService transRecordService;

	private KUser kUser = new KUser();

	public TransRecordController(){
		kUser.setuId(1);
		kUser.setuAccount("admin");
		kUser.setuPassword("admin");
		kUser.setuNickname("admin");
		kUser.setDelFlag(1);
	}

	@RequestMapping("getList.shtml")
	public String getList(Integer offset, Integer limit, Integer transId, HttpServletRequest request){
//		KUser kUser = (KUser) request.getSession().getAttribute(Constant.SESSION_ID);
		BootTablePage list = transRecordService.getList(offset, limit, kUser.getuId(), transId);				
		return JsonUtils.objectToJson(list);
	}
	
	@RequestMapping("getLogContent.shtml")
	public String getLogContent(Integer recordId){
		try {
			String logContent = transRecordService.getLogContent(recordId);
			return ResultDto.success(logContent.replace("\r\n", "<br/>"));
		} catch (IOException e) {
			e.printStackTrace();
			return ResultDto.fail(e.getMessage());
		} 
	}
}
