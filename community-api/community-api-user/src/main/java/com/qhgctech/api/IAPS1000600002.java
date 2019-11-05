package com.qhgctech.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1000600002In;

/*
 * 用户注册接口
 */
public interface IAPS1000600002 extends BaseUserService {
	
	@RequestMapping("/APS1000600002")
	ResponseBase process(@RequestBody APS1000600002In aps1000600002In);
}
