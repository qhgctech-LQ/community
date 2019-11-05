package com.qhgctech.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1100600001In;

/*
 * 	物业用户注册接口
 */
public interface IAPS1100600001 extends BasePropertyService {

	@RequestMapping("/APS1100600001")
	ResponseBase process(@RequestBody APS1100600001In aps1100600001In);
}
