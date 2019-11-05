package com.qhgctech.api;

import org.springframework.web.bind.annotation.RequestMapping;

import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1000600004In;

/*
 * 用户使用token进行登录
 */
public interface IAPS1000600004 extends BaseUserService {
	
	@RequestMapping("/APS1000600004")
	ResponseBase process(APS1000600004In aps1000600004In);
}
