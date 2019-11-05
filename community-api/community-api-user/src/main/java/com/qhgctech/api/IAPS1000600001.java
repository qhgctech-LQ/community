package com.qhgctech.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1000600001In;

/*
 * 用户ID查询接口
 */
public interface IAPS1000600001 extends BaseUserService {
	
	@RequestMapping("/APS1000600001")
	ResponseBase process(@RequestBody APS1000600001In aps1000600001In);
}
