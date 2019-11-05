package com.qhgctech.payment.protocol.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.lf5.util.StreamUtils;

import com.qhgctech.payment.exception.AdapterErr;
import com.qhgctech.payment.exception.CommonErr;
import com.qhgctech.payment.exception.PaymentException;
import com.qhgctech.payment.protocol.ConfigHelper;
import com.qhgctech.payment.protocol.IAdapter;
import com.qhgctech.payment.protocol.utils.RuntimeUtils;

public class HttpSender implements IAdapter {

	private static final Log log = LogFactory.getLog(HttpSender.class);
	private SenderConfig cfg;
	
	public byte[] start(byte[] bytes, HashMap<String, String> hashMap) {
		this.cfg = (SenderConfig)ConfigHelper.LoadConfig(hashMap,SenderConfig.class);
		
		byte[] respBytes = this.process(bytes);
		return respBytes;
	}

	private byte[] process(byte[] bytes) {
		String url = this.cfg.url;
		byte[] respByts = ArrayUtils.EMPTY_BYTE_ARRAY;
		//HTTP方法
		boolean post;
		if(this.cfg.method.equalsIgnoreCase("GET")) {
			post = false;
		}else if (this.cfg.method.equalsIgnoreCase("POST")) {
			post = true;
		}else {
			throw new PaymentException(AdapterErr.HTTP.UnsupportedMethod,this.cfg.method);
		}
		
		byte[] getContent = ArrayUtils.EMPTY_BYTE_ARRAY;
		byte[] postContent = ArrayUtils.EMPTY_BYTE_ARRAY;
		
		//分割开GET和POST部分
		if(!post) {
			getContent = HttpUtils.Encode(bytes, cfg._encodeGet,cfg.encoding);
		}else {
			postContent = HttpUtils.Encode(bytes, cfg._encodePost,cfg.encoding);
		}
		
		try {
			url += new String(getContent,this.cfg.encoding);
		}catch(UnsupportedEncodingException ex) {
			throw new PaymentException(CommonErr.UnsupportedEncoding,this.cfg.encoding);
		}
		
		//将处理好的目标地址转换为URL对象
		URL _url;
		try {
			_url = new URL(url);
		}catch(MalformedURLException ex) {
			throw new PaymentException(AdapterErr.HTTP.UrlSyntaxError,url);
		}
		
		if(log.isDebugEnabled()) {
			log.debug("HTTP[发送数据] url:"+ _url + " \n psot:\n" + RuntimeUtils.PrintHex(postContent, cfg._encoding));
		}
		
		try {
			respByts = this.sendWithHttpClient(_url, post, postContent);
			if(log.isDebugEnabled()) {
				log.debug("HTTP[接受数据] url:"+ _url + " \n psot:\n" + RuntimeUtils.PrintHex(respByts, cfg._encoding));
			}
			respByts = HttpUtils.Decode(respByts, cfg._decodeResult, cfg.encoding);
			if(log.isDebugEnabled()) {
				log.debug("HTTP[接受数据-解码后] url:"+ _url + " \n psot:\n" + RuntimeUtils.PrintHex(respByts, cfg._encoding));
			}
		}catch(PaymentException ex) {
			respByts = ex.getLocalizedMessage().getBytes();
			log.error("HTTP[接收数据] 遇到异常" +ex.getLocalizedMessage());
		}
		return respByts;
	}

	private byte[] sendWithHttpClient(URL _url, boolean post, byte[] postContent) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			// Create local HTTP context
			// 创建本地http上下文对象
			HttpContext localContext = new BasicHttpContext();

			// 建立连接
			HttpResponse response = null;
			if(!post){
				HttpGet httpget = new HttpGet(_url.toString());

				//设置HTTP协议头部,不用手动设置会自动设置的，手动设置报错
//				httpget.setHeader("Content-Length", String.valueOf(postContent.length));
				//设置HTTP头部类型
				httpget.setHeader("Content-Type", cfg.contextType);

				//发出请求
				response = httpclient.execute(httpget, localContext);
				if(log.isDebugEnabled()){
					log.debug("--------------发送GET信息完毕-----------------");
					log.debug("Content-Length:" + postContent.length);
					log.debug("Content-Type:" + cfg.contextType);
					log.debug("POST-CONTENT:" + new String(postContent, cfg._encoding));
				}
			}else{
				HttpPost httpost = new HttpPost(_url.toString());
				//设置HTTP协议头部,不用手动设置会自动设置的，手动设置报错
//				httpost.setHeader("Content-Length", String.valueOf(postContent.length));
				//设置HTTP头部类型
				if(cfg.contextType != null && cfg.contextType.length() > 0)
					httpost.setHeader("Content-Type", cfg.contextType);
				// 设置请求参数
				httpost.setEntity(new ByteArrayEntity(postContent));
				response = httpclient.execute(httpost, localContext);
				if(log.isDebugEnabled()){
					log.debug("--------------发送POST信息完毕-----------------");
					log.debug("Content-Length:" + postContent.length);
					log.debug("Content-Type:" + cfg.contextType);
					log.debug("POST-CONTENT:" + new String(postContent, cfg._encoding));
				}
			}        	

			// 获得响应内容
			HttpEntity entity = response.getEntity();

			if(log.isDebugEnabled()){
				log.debug("--------------收到应答信息-----------------");
				log.debug(response.getStatusLine());
				// 获得响应头
				Header[] headers = response.getAllHeaders();
				for (int i = 0; i < headers.length; i++) {
					log.debug(headers[i]);
				}
				log.debug("----------------------------------------");
			}
			//HttpEntity流是不能重复读取的，所以只能读取一次,updated by 魏文婷 at 20140715
			return StreamUtils.getBytes(entity.getContent());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new PaymentException(AdapterErr.HTTP.HttpFail, e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new PaymentException(AdapterErr.HTTP.HttpFail, e.getLocalizedMessage());
		} finally {
			//释放连接
			httpclient.getConnectionManager().shutdown();
		}
	}

	public void stop() {
		
	}

}
