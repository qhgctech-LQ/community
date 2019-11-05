package com.qhgctech.mapper.plugin.api;

import com.netflix.zuul.context.RequestContext;

public interface IBasePluginParser {

	public byte[] decryAndCheckSign(RequestContext context) throws Exception;
	
	public byte[] encryptAndSign(RequestContext context) throws Exception;
}
