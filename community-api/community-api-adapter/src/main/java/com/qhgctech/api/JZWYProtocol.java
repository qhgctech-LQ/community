package com.qhgctech.api;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qhgctech.base.ResponseBase;

public interface JZWYProtocol extends BaseProtocolService {

	@RequestMapping("/APS1200600001")
	ResponseBase send(@RequestBody HashMap<String,Object> hashMap);
}
