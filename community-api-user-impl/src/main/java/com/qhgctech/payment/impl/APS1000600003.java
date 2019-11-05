package com.qhgctech.payment.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qhgctech.api.IAPS1000600003;
import com.qhgctech.base.BaseApiService;
import com.qhgctech.base.ResponseBase;
import com.qhgctech.entity.APS1000600003In;
import com.qhgctech.entity.APS1000600003Out;
import com.qhgctech.payment.dao.UserDao;
import com.qhgctech.utils.DateUtils;
@RestController
public class APS1000600003 extends BaseApiService implements IAPS1000600003 {
	@Autowired
	private UserDao userDao;

	
	public ResponseBase process(@RequestBody APS1000600003In aps1000600003In) {
		APS1000600003Out aps1000600003Out = userDao.login(aps1000600003In);
		if(aps1000600003Out != null) {
			String loginTime = DateUtils.DateToString(new Date(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN);
			String lastLoginTime = aps1000600003Out.getLoginTime()==null?DateUtils.DateToString(new Date(), DateUtils.DATE_TO_STRING_DETAIAL_PATTERN):aps1000600003Out.getLoginTime();
			int count = aps1000600003Out.getCount() + 1;
			aps1000600003Out.setLoginTime(loginTime);
			aps1000600003Out.setLastLoginTime(lastLoginTime);
			aps1000600003Out.setCount(count);
			userDao.updateUser(aps1000600003Out);
			return setResult(200,"用户登录成功",aps1000600003Out);
		}else {
			return setResultError("用户名密码错误!");
		}
	}
	

}
