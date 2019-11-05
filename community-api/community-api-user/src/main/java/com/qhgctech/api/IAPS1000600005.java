package com.qhgctech.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1000600005In;

/*
 * 用户修改接口
 */
public interface IAPS1000600005 extends BaseUserService {
	
	@RequestMapping("/APS1000600005")
	ResponseBase process(APS1000600005In aps1000600005In);
}
