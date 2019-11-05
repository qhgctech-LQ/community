package com.qhgctech.payment.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qhgctech.entity.APS1000600001In;
import com.qhgctech.entity.APS1000600001Out;
import com.qhgctech.entity.APS1000600002In;
import com.qhgctech.entity.APS1000600003In;
import com.qhgctech.entity.APS1000600003Out;

public interface UserDao {
	
	@Select("select user_id userId,login_name loginName,password,vsername,mobile,email,create_time createTime,login_time loginTime,last_login_time lastLoginTime,count from yh_user where user_id =#{userId}")
	APS1000600001Out findByID(APS1000600001In aps1000600001In);
	
	@Insert("INSERT  INTO yh_user (user_id,login_name,password,vsername,mobile,email,create_time,login_time,last_login_time,count) VALUES (#{userId}, #{loginName},#{password},#{vsername},#{mobile},#{email},#{createTime},#{loginTime},#{lastLoginTime},#{count});")
	Integer insertUser(APS1000600002In aps1000600002In);
	
	@Select("select user_id userId,login_name loginName,password,vsername,mobile,email,create_time createTime,login_time loginTime,last_login_time lastLoginTime,count from yh_user where login_name=#{loginName} and password=#{password}")
	APS1000600003Out login(APS1000600003In aps1000600003In);
	
	//@Param("userId") Integer userId
	@Update("update yh_user set login_time=#{loginTime},last_login_time=#{lastLoginTime},count=#{count} where user_id=#{userId}")
	Integer updateUser(Object obj);
}
