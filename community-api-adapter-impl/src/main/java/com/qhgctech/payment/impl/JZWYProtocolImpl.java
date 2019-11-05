package com.qhgctech.payment.impl;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestBody;

import com.qhgctech.api.JZWYProtocol;
import com.qhgctech.base.BaseApiService;
import com.qhgctech.base.ResponseBase;
import com.qhgctech.payment.protocol.http.HttpSender;
/*
 * 极致物业接出实现类
 */
public class JZWYProtocolImpl extends BaseApiService implements JZWYProtocol {

	public ResponseBase send(@RequestBody HashMap<String,Object> hashMap) {
		byte[] reqBytes = encryptAndSign(hashMap);
		HttpSender send = new HttpSender();
		HashMap<String,String> configHash = new HashMap<String,String>();
		configHash.put("", "");
		byte[] respBytes = send.start(reqBytes, configHash);
		respBytes = decryAndCheckSign(respBytes);
		return null;
	}

	private byte[] encryptAndSign(HashMap<String, Object> hashMap) {
		
		return null;
	}
	
	public byte[] decryAndCheckSign(byte[] respBytes) {
		
		return null;
	}

}
