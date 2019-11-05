package com.qhgctech.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1000600003In;

/*
 * 用户登录接口
 */
public interface IAPS1000600003 extends BaseUserService {
	
	@RequestMapping("/APS1000600003")
	ResponseBase process(@RequestBody APS1000600003In aps1000600003In);
}
