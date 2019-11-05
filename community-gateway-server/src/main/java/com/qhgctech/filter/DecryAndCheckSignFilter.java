package com.qhgctech.filter;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.servlet4preview.http.HttpServletRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.qhgctech.dao.ZuulDao;
import com.qhgctech.mapper.plugin.api.IBasePluginParser;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DecryAndCheckSignFilter extends ZuulFilter {
	@Autowired
	private ZuulDao zuulDao;
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		Object originalRequestPath = context.get(FilterConstants.REQUEST_URI_KEY);
		String serverName = context.get("proxy").toString();
		String newRequestPath = originalRequestPath.toString();
		String[] pathArrs = newRequestPath.split("/");
		if("POST".equalsIgnoreCase(request.getMethod()) && pathArrs.length>=2) {
			String channelName = pathArrs[1];
			String url = newRequestPath.replace("/"+channelName, "");
			String code = zuulDao.serverUrlToCode(serverName,url);
			String pluginName = zuulDao.findPluginName(channelName);
			//获取插件类，对报文进行验签和解密
			if(null != pluginName && !"".equals(pluginName)) {
				try {
					Class<?> cls = Class.forName(pluginName);
					IBasePluginParser parser = (IBasePluginParser)cls.newInstance();
					byte[] reqBodyBytes = parser.decryAndCheckSign(context);
					context.setRequest(new HttpServletRequestWrapper(request) {
						@Override
						public ServletInputStream getInputStream() throws IOException{
							return new ServletInputStreamWrapper(reqBodyBytes);
						}
						@Override
						public int getContentLength() {
							return reqBodyBytes.length;
						}
						@Override
						public long getContentLengthLong() {
							return reqBodyBytes.length;
						}
					});
					context.set("channel", channelName);
				} catch (Exception e) {
					log.error("网关验签解密失败!错误原因:"+e.getMessage());
					context.setSendZuulResponse(false);
					context.setResponseStatusCode(501);
					context.setResponseBody("网关验签解密失败!");
					context.set("isSuccess",false);
				}
			}
			//对url进行地址映射
			if (null != code) {
				context.put(FilterConstants.REQUEST_URI_KEY, code);
			}
			return null;
		}
		context.setSendZuulResponse(false);
		context.setResponseStatusCode(401);
		context.setResponseBody("调用网关url地址有误!");
		context.set("isSuccess",false);
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;

	}

}
