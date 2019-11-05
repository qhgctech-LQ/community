package com.qhgctech.mapper.plugin.api.impl;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StreamUtils;

import com.netflix.zuul.context.RequestContext;
import com.qhgctech.mapper.plugin.api.IBasePluginParser;

public class BasePluginParserImpl implements IBasePluginParser {

	//验签和解密实现方法
	public byte[] decryAndCheckSign(RequestContext context) throws Exception {
		System.out.println("==============decryAndCheckSign=============");
		HttpServletRequest request = context.getRequest();
		InputStream in = request.getInputStream();
		byte[] reqBytes = StreamUtils.copyToByteArray(in);
		System.out.println("请求报文内容:"+new String(reqBytes,"UTF-8"));
		System.out.println("==============decryAndCheckSign=============");
		return reqBytes;
	}

	//加签和加密实现方法
	public byte[] encryptAndSign(RequestContext context) throws Exception{
		System.out.println("================encryptAndSign================");
		InputStream in = context.getResponseDataStream();
		byte[] respBytes = StreamUtils.copyToByteArray(in);
		System.out.println("请求报文内容:"+new String(respBytes,"UTF-8"));
		System.out.println("================encryptAndSign================");
		return respBytes;
	}

}
