package com.qhgctech.payment.exception;


/**
 * 由适配器定义的错误码
 * 
 * @author 
 * 
 */
public class AdapterErr {
	public static final ErrCode LoadConfigFail = new ErrCode("ADP001", "装载适配器配置时发生错误");
	public static final ErrCode FillConfigFail = new ErrCode("ADP002", "向配置对象中填充配置信息时发生错误");
	public static final ErrCode NotSupportedOperation = new ErrCode("ADP004", "操作不支持");

	/** 与HTTP适配器相关的错误 */
	public static class HTTP {
		public static final ErrCode InitJettyFail = new ErrCode("AHTP01", "启动Jetty服务时发生错误");
		public static final ErrCode StartJettyFail = new ErrCode("AHTP02", "启动Jetty服务时发生错误");
		public static final ErrCode StopJettyFail = new ErrCode("AHTP03", "停止Jetty服务时发生错误");

		public static final ErrCode IOFail = new ErrCode("AHTP04", "发生IO错误");
		public static final ErrCode UnsupportedMethod = new ErrCode("AHTP05", "不支持指定的HTTP方法(只支持GET和POST)");
		public static final ErrCode UrlSyntaxError = new ErrCode("AHTP06", "给定的URL中存在错误");
		public static final ErrCode HttpFail = new ErrCode("AHTP07", "服务器端返回HTTP错误");
		public static final ErrCode DecodeFail = new ErrCode("AHTP08", "对报文进行解码时发生错误");
		public static final ErrCode UnsupportedDecodeMethod = new ErrCode("AHTP09", "不支持的解码方法");
		public static final ErrCode EncodeFail = new ErrCode("AHTP10", "对报文进行编码时发生错误");
		public static final ErrCode UnsupportedEncodeMethod = new ErrCode("AHTP11", "不支持的编码方法");
		public static final ErrCode NoGETPOSTSeperator = new ErrCode("AHTP12", "报文中未使用\\0分隔GET和POST部分");
		public static final ErrCode GetServletPathFail = new ErrCode("AHTP13", "执行getServletPath()时发生错误");
		public static final ErrCode GetQueryStringFail = new ErrCode("AHTP14", "执行getQueryString()时发生错误");
	}
	
	/** 与HTTPS适配器相关的错误 */
	public static class HTTPS {
		public static final ErrCode InitJettyFail = new ErrCode("AHTP01", "启动Jetty服务时发生错误");
		public static final ErrCode StartJettyFail = new ErrCode("AHTP02", "启动Jetty服务时发生错误");
		public static final ErrCode StopJettyFail = new ErrCode("AHTP03", "停止Jetty服务时发生错误");

		public static final ErrCode IOFail = new ErrCode("AHTP04", "发生IO错误");
		public static final ErrCode UnsupportedMethod = new ErrCode("AHTP05", "不支持指定的HTTPS方法(只支持GET和POST)");
		public static final ErrCode UrlSyntaxError = new ErrCode("AHTP06", "给定的URL中存在错误");
		public static final ErrCode HttpsFail = new ErrCode("AHTP07", "服务器端返回HTTPS错误");
		public static final ErrCode DecodeFail = new ErrCode("AHTP08", "对报文进行解码时发生错误");
		public static final ErrCode UnsupportedDecodeMethod = new ErrCode("AHTP09", "不支持的解码方法");
		public static final ErrCode EncodeFail = new ErrCode("AHTP10", "对报文进行编码时发生错误");
		public static final ErrCode UnsupportedEncodeMethod = new ErrCode("AHTP11", "不支持的编码方法");
		public static final ErrCode NoGETPOSTSeperator = new ErrCode("AHTP12", "报文中未使用\\0分隔GET和POST部分");
		public static final ErrCode GetServletPathFail = new ErrCode("AHTP13", "执行getServletPath()时发生错误");
		public static final ErrCode GetQueryStringFail = new ErrCode("AHTP14", "执行getQueryString()时发生错误");
	}
	
	/** 与MQ适配器相关的错误 */
	public static class MQ {
		public static final ErrCode OpenQMFail = new ErrCode("AMQ001", "打开队列管理器时发生错误");
		public static final ErrCode OpenQFail = new ErrCode("AMQ002", "打开队列时发生错误");
		public static final ErrCode WriteQFail = new ErrCode("AMQ003", "向队列里写时发生错误");
		public static final ErrCode ReadQFail = new ErrCode("AMQ004", "从队列中读时发生错误");
		public static final ErrCode CloseQFail = new ErrCode("AMQ005", "关闭队列时发生错误");
		public static final ErrCode CloseQMFail = new ErrCode("AMQ006", "关闭队列管理器时发生错误");
		public static final ErrCode UnsupportedAdapterMode = new ErrCode("AMQ007", "不支持的适配器模式[mode] (目前支持LISTENER|SENDER|DUPLEX)");
		public static final ErrCode UnsupportedClass = new ErrCode("AMQ008", "不支持的适配器类");
		public static final ErrCode GetPLMessgeFail = new ErrCode("AMQ009", "获取批量消息错误");
		public static final ErrCode ServerMQFail = new ErrCode("AMQ010", "MQ服务端处理错误，退出循环");
	}

	/** 与TCP适配器相关的错误 */
	public static class TCP {
		public static final ErrCode StartServerFail = new ErrCode("ATCP01", "启动Socket服务器时发生错误");
		public static final ErrCode StopServerFail = new ErrCode("ATCP02", "停止Socket服务器时发生错误");
		public static final ErrCode AcceptFail = new ErrCode("ATCP03", "监听请求(accept)时发生错误");
		public static final ErrCode ConnectFail = new ErrCode("ATCP04", "无法建立到目标的连接");
		public static final ErrCode CloseSocketFail = new ErrCode("ATCP05", "关闭Socket时发生错误");
		public static final ErrCode ReadFail = new ErrCode("ATCP06", "与被测系统通讯(read)时发生错误");
		public static final ErrCode WriteFail = new ErrCode("ATCP07", "与被测系统通讯(write)时发生错误");

		public static final ErrCode InitPluginFail = new ErrCode("ATCP08", "初始化TCP适配器插件时发生错误");

		public static final ErrCode UnsupportedPacketType = new ErrCode("ATCP010", "不支持的packetType配置(fix|len|seq|func)");

		public static final ErrCode FixLenMustMustPostive = new ErrCode("ATCP11", "fixLen必须为正数");

		public static final ErrCode LenStartMustNotNegative = new ErrCode("ATCP11", "lenStart不能为负数");
		public static final ErrCode LenLenMustPostive = new ErrCode("ATCP12", "lenLen必须为正数");
		public static final ErrCode UnsupportedLenLen = new ErrCode("ATCP13", "lenType为d或D时不支持给定的lenLen配置(1|2|3|4|8)");
		public static final ErrCode UnsupportedLenType = new ErrCode("ATCP14", "不支持的lenType配置(d|D|BCD|text)");
		public static final ErrCode UnsupportedLenAlign = new ErrCode("ATCP15", "不支持的lenAlign配置(left|right)");
		public static final ErrCode UnsupportedLenEncoding = new ErrCode("ATCP16", "不支持由lenEncoding指定的编码");
		public static final ErrCode LenActuralLenMustPostive = new ErrCode("ATCP17", "lenActuralLen必须为正数");
		public static final ErrCode LenFillingCharSyntaxError = new ErrCode("ATCP18", "lenFillingChar的语法不正确");
		public static final ErrCode UnsupportedLenRadix = new ErrCode("ATCP19", "不支持指定的lenRadix配置");

		public static final ErrCode SeparatorSyntaxError = new ErrCode("ATCP20", "分隔符的语法不正确");
		public static final ErrCode SeparatorLenZero = new ErrCode("ATCP21", "分隔符的长度不能为0");

		public static final ErrCode FuncClassNotFound = new ErrCode("ATCP22", "找不到指定的外部分包函数");
		public static final ErrCode FuncExecFail = new ErrCode("ATCP25", "分包函数调用失败");
		
		public static final ErrCode TagStartMustNotNegative = new ErrCode("ATCP26", "tagStart不能为负数");
		public static final ErrCode TagLenMustPostive = new ErrCode("ATCP27", "tagLen必须为正数");
		public static final ErrCode UnsupportedTagType = new ErrCode("ATCP28", "不支持的tagType配置(d|D|BCD|text)");
		public static final ErrCode UnsupportedTagLen = new ErrCode("ATCP29", "tagType为d或D时不支持给定的tagLen配置(1|2|3|4|8)");
		public static final ErrCode UnsupportedTagAlign = new ErrCode("ATCP30", "不支持的tagAlign配置(left|right)");
		public static final ErrCode UnsupportedTagEncoding = new ErrCode("ATCP31", "不支持由tagEncoding指定的编码");
		public static final ErrCode LenActuralTagMustPostive = new ErrCode("ATCP32", "tagActuralLen必须为正数");
		public static final ErrCode TagFillingCharSyntaxError = new ErrCode("ATCP33", "tagFillingChar的语法不正确");
		public static final ErrCode UnsupportedTagRadix = new ErrCode("ATCP34", "不支持指定的tagRadix配置");
		
		public static final ErrCode TagLenStartMustNotNegative = new ErrCode("ATCP35", "tagLenStart不能为负数");
		public static final ErrCode TagLenLenMustPostive = new ErrCode("ATCP36", "tagLenLen必须为正数");
		public static final ErrCode UnsupportedTagLenType = new ErrCode("ATCP37", "不支持的tagLenType配置(d|D|BCD|text)");
		public static final ErrCode UnsupportedTagLenLen = new ErrCode("ATCP38", "tagLenType为d或D时不支持给定的tagLenLen配置(1|2|3|4|8)");
		public static final ErrCode UnsupportedTagLenAlign = new ErrCode("ATCP39", "不支持的tagLenAlign配置(left|right)");
		public static final ErrCode UnsupportedTagLenEncoding = new ErrCode("ATCP40", "不支持由tagLenEncoding指定的编码");
		public static final ErrCode LenActuralTagLenMustPostive = new ErrCode("ATCP41", "tagLenActuralLen必须为正数");
		public static final ErrCode TagLenFillingCharSyntaxError = new ErrCode("ATCP42", "tagLenFillingChar的语法不正确");
		public static final ErrCode UnsupportedTagLenRadix = new ErrCode("ATCP43", "不支持指定的tagLenRadix配置");
		public static final ErrCode TagLenMustNoOverlap = new ErrCode("ATCP44", "标识位和长度位不能重叠");
		public static final ErrCode UnsupportedTimeUnit = new ErrCode("ATCP45", "不支持的时间单位类型");
	}

	
}