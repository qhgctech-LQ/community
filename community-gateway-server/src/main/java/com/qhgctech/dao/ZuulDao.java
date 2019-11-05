package com.qhgctech.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ZuulDao {

	@Select("SELECT CODE FROM ZUUL_MAPPING WHERE SERVER_NAME=#{serverName} AND URL=#{url}")
	String serverUrlToCode(@Param("serverName") String serverName, @Param("url") String url);
	
	@Select("SELECT CHANNEL_PLUGIN FROM ZUUL_CHANNEL WHERE CHANNEL_NAME=#{channelName}")
	String findPluginName(@Param("channelName") String channelName);
}
