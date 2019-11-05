package com.qhgctech.payment.protocol;

import java.util.HashMap;

public interface IAdapter {

	public byte[] start(byte[] bytes,HashMap<String,String> hashMap);
	
	public void stop();
}
