package com.qhgctech.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.qhgctech.dao.ZuulDao;
import com.qhgctech.mapper.plugin.api.IBasePluginParser;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EncryptAndSignFilter extends ZuulFilter {
	@Autowired
	private ZuulDao zuulDao;
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		try {
			String channelName = (String)context.get("channel");
			String pluginName = zuulDao.findPluginName(channelName);
			if(null != pluginName && !"".equals(pluginName)) {
				Class<?> cls = Class.forName(pluginName);
				IBasePluginParser parser = (IBasePluginParser)cls.newInstance();
				byte[] respBodyBytes = parser.encryptAndSign(context);
				context.setResponseBody(new String(respBodyBytes));
			}
		} catch (Exception e) {
			log.error("网关加签加密失败!错误原因:"+e.getMessage());
			context.setSendZuulResponse(false);
			context.setResponseStatusCode(502);
			context.setResponseBody("网关加签加密失败!");
			context.set("isSuccess",false);
		}
		return null;

	}

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;

	}

}
