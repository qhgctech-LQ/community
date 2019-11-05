package com.qhgctech.payment.protocol.http;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.SystemUtils;

import com.qhgctech.payment.exception.AdapterErr;
import com.qhgctech.payment.exception.CommonErr;
import com.qhgctech.payment.exception.PaymentException;
import com.qhgctech.payment.protocol.utils.RuntimeUtils;

public class HttpUtils {

	/**
	 * 检查发起端配置
	 */
	static void CheckConfig(SenderConfig cfg) {
		try {
			cfg._encoding = Charset.forName(cfg.encoding);
		} catch (Exception ex) {
			throw new PaymentException(CommonErr.UnsupportedEncoding, cfg.encoding);
		}

		try {
			cfg._encodeGet = analyseEncodeString(cfg.encodeGet, true);
		} catch (Exception ex) {
			throw new PaymentException(AdapterErr.HTTP.UnsupportedEncodeMethod, "encodeGet", ex);
		}
		try {
			cfg._encodePost = analyseEncodeString(cfg.encodePost, true);
		} catch (Exception ex) {
			throw new PaymentException(AdapterErr.HTTP.UnsupportedEncodeMethod, "encodePost", ex);
		}
		try {
			cfg._decodeResult = analyseEncodeString(cfg.decodeResult, false);
		} catch (Exception ex) {
			throw new PaymentException(AdapterErr.HTTP.UnsupportedDecodeMethod, "decodeResult", ex);
		}
	}

	/**
	 * 工具函数 使用指定的编码方式对给定的字节数组进行编码 目前支持base64和urlencode
	 * 
	 * @param bytes
	 *            被编码的字节数组
	 * @param methods
	 *            编码方法
	 * @param encoding
	 *            编码
	 * @return 编码后的字节数组
	 */
	static byte[] Encode(byte[] bytes, int[] methods, String encoding) {
		for (int i = 0; i < methods.length; i++)
			try {
				switch (methods[i]) {
				case 0:
					break;
				case 1:
					bytes = Base64.encodeBase64(bytes);
					break;
				case 2:
					bytes = URLEncoder.encode(new String(bytes, encoding), encoding).getBytes(encoding);
					break;
				}
			} catch (Exception ex) {
				throw new PaymentException(AdapterErr.HTTP.EncodeFail, "method: " + printEncodeString(methods, true) + " bytes: " + SystemUtils.LINE_SEPARATOR + RuntimeUtils.PrintHex(bytes, Charset.forName(encoding)), ex);
			}

		return bytes;
	}

	/**
	 * 工具函数 使用指定的解码方式对给定的字节数组进行解码 目前支持base64和urldecode
	 * 
	 * @param bytes
	 *            被解码的字节数组
	 * @param methods
	 *            解码方法
	 * @param encoding
	 *            编码
	 * @return 解码后的字节数组
	 * @throws UnsupportedEncodingException
	 */
	static byte[] Decode(byte[] bytes, int[] methods, String encoding) {
		for (int i = 0; i < methods.length; i++)
			try {
				switch (methods[i]) {
				case 0:
					break;
				case 1:
					bytes = Base64.decodeBase64(bytes);
					break;
				case 2:
					bytes = URLDecoder.decode(new String(bytes, encoding), encoding).getBytes(encoding);
					break;
				}
			} catch (Exception ex) {
				throw new PaymentException(AdapterErr.HTTP.EncodeFail, "method: " + printEncodeString(methods, true) + " bytes: " + SystemUtils.LINE_SEPARATOR + RuntimeUtils.PrintHex(bytes, Charset.forName(encoding)), ex);
			}

		return bytes;
	}

	/**
	 * 工具函数 解析编解码字符串
	 * 
	 * @param method
	 *            编解码字符串
	 * @param mode
	 *            true为编码 false为解码
	 * @return 解析之后的内部表示
	 */
	private static int[] analyseEncodeString(String method, boolean mode) {
		if (method == null || method.length() == 0)
			return new int[0];

		String[] methods = method.split("\\|");
		int[] result = new int[methods.length];

		for (int i = 0; i < methods.length; i++)
			if (methods[i].length() == 0)
				result[i] = 0;
			else if (methods[i].equalsIgnoreCase("base64"))
				result[i] = 1;
			else if (methods[i].equalsIgnoreCase("urlencode") && mode)
				result[i] = 2;
			else if (methods[i].equalsIgnoreCase("urldecode") && !mode)
				result[i] = 2;
			else
				throw new PaymentException(AdapterErr.HTTP.UnsupportedEncodeMethod, method);

		return result;
	}

	/**
	 * 工具函数 打印编解码字符串
	 * 
	 * @param methods
	 *            编解码方式的内部表示
	 * @param mode
	 *            true为编码 false为解码
	 * @return 编解码字符串
	 */
	private static String printEncodeString(int[] methods, boolean mode) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < methods.length; i++)
			switch (methods[i]) {
			case 0:
				break;
			case 1:
				buffer.append("|base64");
				break;
			case 2:
				buffer.append(mode ? "|urlencode" : "|urldecode");
			}

		return buffer.length() > 0 ? buffer.substring(1) : "";
	}
}
