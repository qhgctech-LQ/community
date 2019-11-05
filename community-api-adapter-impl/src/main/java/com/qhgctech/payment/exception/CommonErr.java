package com.qhgctech.payment.exception;


/**
 * 由common定义的错误
 * 
 * @author 
 * 
 */
public class CommonErr {
	public static final ErrCode GetStartDirFail = new ErrCode("CMN001", "获取系统当前运行路径出错");
	public static final ErrCode ConvertFromStringFail = new ErrCode("CMN002", "无法将字符串转换为期望的类型");
	public static final ErrCode UnsupportedConversionFromString = new ErrCode("CMN003", "无法将字符串转换为期望的类型_指定的目标类型不被支持");
	public static final ErrCode DynamicCompileFail = new ErrCode("CMN004", "动态编译失败");
	public static final ErrCode DynamicCompileSyntaxError = new ErrCode("CMN005", "动态编译失败_编译目标存在语法错误");
	public static final ErrCode UnsupportedEncoding = new ErrCode("CMN006", "不支持的编码名称");
	public static final ErrCode IllegalHexString = new ErrCode("CMN007", "不是有效的HEX字符串");
	public static final ErrCode CreateInstanceFail = new ErrCode("CMN008", "创建类型实例失败");
	public static final ErrCode ConstructorNotFound = new ErrCode("CMN009", "该类所有的构造函数都无法与给定的参数相匹配");
	public static final ErrCode ClassNotFound = new ErrCode("CMN010", "类型未找到(ClassNotFound)");
	public static final ErrCode MethodNotFound = new ErrCode("CMN011", "方法未找到(MethodNotFound)");
	public static final ErrCode MethodNotStatic = new ErrCode("CMN012", "指定名称的方法已找到，但该方法不是静态方法");
	public static final ErrCode MethodRetTypeMismatch = new ErrCode("CMN013", "指定名称的方法已找到，但该方法的返回值和期望的不一致");
	public static final ErrCode CommandNotFound = new ErrCode("CMD014", "命令未找到(CommandNotFound)");
	public static final ErrCode UnsupportedMethod = new ErrCode("CMN015", "不支持的方法名称");

	/** 与创建组件相关的错误 */
	public static class Component {
		public static final ErrCode CreateComponentFail = new ErrCode("CMNC01", "创建组件失败");
		public static final ErrCode ComponentTagRequired = new ErrCode("CMNC02", "要创建的组件未使用@ComponentClass进行标记");
		public static final ErrCode FillComponentPropertyFail = new ErrCode("CMNC03", "设置组件的配置属性项时发生错误");
		public static final ErrCode NullConfig = new ErrCode("CMNC04", "未找到组件的配置属性项");
		public static final ErrCode NullConfigName = new ErrCode("CMNC05", "未指明要创建的组件的名称");
		public static final ErrCode FindConfigObjectClassFail = new ErrCode("CMNC06", "检索组件的配置对象类型时发生错误");
		public static final ErrCode CreateConfigObjectFail = new ErrCode("CMNC07", "创建组件的配置对象时发生错误");
	}

	/** 与IO相关的错误 */
	public static class IO {
		public static final ErrCode FileNotFound = new ErrCode("CMNI01", "文件找不到");
		public static final ErrCode IOReadFail = new ErrCode("CMNI02", "从输入流中读取数据时发生IO错误");
		public static final ErrCode IOWriteFail = new ErrCode("CMNI03", "从输入流中读取数据时发生IO错误");
		public static final ErrCode CloseInputStreamFail = new ErrCode("CMNI04", "关闭输入流时发生IO错误");
		public static final ErrCode CreateFileFail = new ErrCode("CMNI05", "创建文件失败");
		public static final ErrCode CloseOutputStreamFail = new ErrCode("CMNI06", "关闭输出流时发生IO错误");
		public static final ErrCode DeleteTargetNotFound = new ErrCode("CMNI07", "被删除的目标不存在");
		public static final ErrCode DeleteTargetNotDir = new ErrCode("CMNI08", "被删除的目标不是一个目录");
	}

	/** 与数据访问层相关的错误 */
	public static class Dal {
		public static final ErrCode FactoryInitFail = new ErrCode("CMND01", "初始化数据源工厂类时发生错误");
		public static final ErrCode FactoryNotInitialized = new ErrCode("CMND02", "数据层尚未成功初始化");
		public static final ErrCode GetBeanDALFail = new ErrCode("CMND03", "获取数据访问对象时发生错误");
		public static final ErrCode HibernateInitFail = new ErrCode("CMND04", "Hibernate初始化失败");
		public static final ErrCode HibernateNotInitialized = new ErrCode("CMND05", "Hibernate尚未成功初始化");
		public static final ErrCode XmlFail = new ErrCode("CMND06", "读写xml时发生错误");
		public static final ErrCode ConnectedFail = new ErrCode("CMND07", "读写xml时发生错误");
	}

	/** 与内部报文处理相关的错误 */
	public static class Net {
		public static final ErrCode ReceiveMessageFail = new ErrCode("CMNM01", "解析接收到的消息时发生错误");
		public static final ErrCode Timeout = new ErrCode("CMNM02", "通讯超时");
		public static final ErrCode UnknownMessageType = new ErrCode("CMNM03", "消息类型无法识别");

		public static final ErrCode StartMessageSeverFail = new ErrCode("CMNM04", "建立消息通道服务器时发生错误");
		public static final ErrCode StopMessageServerFail = new ErrCode("CMNM05", "停止消息通道服务器时发生错误");
		public static final ErrCode AcceptFail = new ErrCode("CMNM06", "尝试进行accept()的时候发生错误");
		public static final ErrCode ConnectFail = new ErrCode("CMNM07", "尝试建立到远端系统的连接时发生错误");
		public static final ErrCode CloseSocketFail = new ErrCode("CMNM08", "尝试关闭消息连接时发生错误");
		
		public static final ErrCode ExecCommandFail = new ErrCode("CMNM09", "执行远程控制命令时发生错误");
		
		
	}
	
	public static class MsgService{
		public static final ErrCode UnpackMessageFail = new ErrCode("CMNS01", "报文解析时发生错误");
	}

	/** 与XML读写相关的错误 */
	public static class Xml {
		public static final ErrCode LoadNullInputStream = new ErrCode("CMNX01", "提供给xml读取器的输入流为null");
		public static final ErrCode LoadNullString = new ErrCode("CMNX02", "提供给xml读取器的字符串为null");
		public static final ErrCode SAXFail = new ErrCode("CMNX03", "xml中存在语法错误");
		public static final ErrCode LoadXmlFail = new ErrCode("CMNX04", "读取xml时发生错误");
		public static final ErrCode XPathSyntaxError = new ErrCode("CMNX05", "xpath语法存在错误");
		public static final ErrCode WriteNullOutputStream = new ErrCode("CMNX06", "用于承载xml的输出流为null");
		public static final ErrCode WriteXmlFail = new ErrCode("CMNX07", "向输出流写入xml时发生错误");
	}
	
	public static class Conf{
		public static final ErrCode LicenseFileNotFound = new ErrCode("CMNCF01", "找不到License服务配置信息");
		public static final ErrCode LicenseFileIOError = new ErrCode("CMNCF02", "读取License服务配置信息错误");
		public static final ErrCode LicenseHostNotFound = new ErrCode("CMNCF03", "在配置文件中未找到License服务地址信息");
		public static final ErrCode LicensePortNotFound = new ErrCode("CMNCF04", "在配置文件中未找到License服务端口信息或无法解析为一个整数");
		public static final ErrCode LicensePortMustPostive = new ErrCode("CMNCF05", "License服务端口必须为正数");

	}
	
	/** 与度量组件相关的错误 */
	public static class Measure {
		public static final ErrCode SnapDuplicated = new ErrCode("CMMTE01", "同一个类中存在同名的时间探测点");
		public static final ErrCode SnapNotExisted = new ErrCode("CMMTE02", "要计算的执行点没有记录");
	}
}
