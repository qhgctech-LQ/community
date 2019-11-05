package com.qhgctech.payment.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qhgctech.api.IAPS1000600002;
import com.qhgctech.base.BaseApiService;
import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1000600002In;
import com.qhgctech.payment.dao.UserDao;
import com.qhgctech.utils.DateUtils;
@RestController
public class APS1000600002 extends BaseApiService implements IAPS1000600002 {
	@Autowired
	private UserDao userDao;
	
	@Override
	public ResponseBase process(@RequestBody APS1000600002In aps1000600002In) {
		String createTime = DateUtils.DateToString(new Date(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN);
		aps1000600002In.setCreateTime(createTime);
		aps1000600002In.setCount(0);
		int result = userDao.insertUser(aps1000600002In);
		if(result==1) {
			return setResultSuccess();
		}else {
			return setResultError("注册用户失败!");
		}
	}

}
