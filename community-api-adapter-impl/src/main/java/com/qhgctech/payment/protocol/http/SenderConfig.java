package com.qhgctech.payment.protocol.http;

import java.nio.charset.Charset;

/*
 * HTTP发起端适配器的配置对象
 */
public class SenderConfig {
	/**	目标地址	*/
	public String url;
	
	/**	超时时间(毫秒级)	*/
	public int timeOut = 5000;
	
	/**	HTTP方法 目前支持 "GET"和 "POST"	*/
	public String method;
	
	/**	对HTTP头补进行类型设置	*/
	public String contextType;
	
	/**	报文编码	*/
	public String encoding;
	
	/**	对GET过去的字节流进行编码方式 目前支持base64和urlencode两种	*/
	public String encodeGet;
	/**	对POST过去的字节流进行编码方式 目前支持base64和urlencode两种	*/
	public String encodePost;
	/**	对返回的数据进行解码方式 目前支持base64和urldecode两种	*/
	public String decodeResult;
	
	
	
	//报文编码
	Charset _encoding;
	//编解码方式0-none 1-base64 2-urlencode|urldecode
	int[] _encodeGet;
	int[] _encodePost;
	int[] _decodeResult;
}
