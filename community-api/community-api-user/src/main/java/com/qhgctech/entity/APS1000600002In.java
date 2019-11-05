package com.qhgctech.entity;

import lombok.Data;

@Data
public class APS1000600002In {
	public Integer userId;	//记录标识 主键
	public String loginName;	//登录帐号
	public String password;	//用户密码
	public String vsername;	//用户姓名
	public String mobile;	//手机号
	public String email;	//电子邮箱
	public String createTime;	//创建时间
	public String loginTime;	//登录时间
	public String lastLoginTime;	//上次登录时间
	public Integer count;	//登录次数
}
