package com.qhgctech.payment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qhgctech.api.IAPS1000600001;
import com.qhgctech.base.BaseApiService;
import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1000600001In;
import com.qhgctech.entity.APS1000600001Out;
import com.qhgctech.payment.dao.UserDao;

@RestController
public class APS1000600001 extends BaseApiService implements IAPS1000600001{
	@Autowired
	private UserDao userDao;
	
	@Override
	public ResponseBase process(@RequestBody APS1000600001In aps1000600001In) {
		APS1000600001Out aps1000600001Out = userDao.findByID(aps1000600001In);
		return setResultSuccess(aps1000600001Out==null?"查无此人!":aps1000600001Out);
	}

}
