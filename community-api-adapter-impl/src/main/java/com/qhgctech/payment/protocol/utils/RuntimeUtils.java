package com.qhgctech.payment.protocol.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.enums.EnumUtils;

import com.qhgctech.payment.exception.CommonErr;
import com.qhgctech.payment.exception.PaymentException;

/**
 * 工具类 用于提供一些运行时的基础支持
 * 
 * @author 
 * 
 */
public class RuntimeUtils {
	
	/**
	 * 将异常的信息导出为字符串
	 * 
	 * @param ex
	 *            异常对象
	 * @return 异常的信息
	 */
	public static String PrintEx(Throwable ex) {
		StringWriter w = new StringWriter();
		ex.printStackTrace(new PrintWriter(w));
		return w.toString();
	}
	
	/**
	 * 将一个字符串解析为指定的类型 目前支持String int boolean Enum
	 * 
	 * @param <T>
	 *            期望的类型
	 * @param value
	 *            要转换的字符串
	 * @param cls
	 *            期望的类型
	 * @return 由字符串转换得到的对象 如果给定的字符串为null 则返回null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T FromString(String value, Class<T> cls) {
		if (value == null)
			return null;
		if (cls == null)
			throw new PaymentException(CommonErr.UnsupportedConversionFromString, "class = <null>");

		try {
			if (cls == String.class)
				return (T) value;
			if (cls == int.class || cls == Integer.class)
				return (T) (Integer) Integer.parseInt(value);
			if (cls == boolean.class || cls == Boolean.class)
				return (T) (Boolean) ("true".equalsIgnoreCase(value));
			if (cls.isEnum())
				return (T) EnumUtils.getEnum(cls, value);

			throw new PaymentException(CommonErr.UnsupportedConversionFromString, "[" + value + "] -> " + cls.getName());
		} catch (Exception ex) {
			throw new PaymentException(CommonErr.ConvertFromStringFail, "[" + value + "] -> " + cls.getName(), ex);
		}
	}
	
	/**
	 * 获取一个指定类型的默认值
	 * <p>
	 * <li>对于数字类型来说是0</li>
	 * <li>对于bool类型来说是false</li>
	 * <li>对于字符串类型来说是空字符串</li>
	 * <li>对于数组类型来说是一个长度为0的数组</li>
	 * <li>对于其它类型来说是null</li>
	 * </p>
	 * 
	 * @param <T>
	 *            类型
	 * @param cls
	 *            类型
	 * @return 类型的默认值
	 */
	@SuppressWarnings("unchecked")
	public static <T> T CreateDefaultValue(Class<T> cls) {
		if (cls == boolean.class || cls == Boolean.class)
			return (T) (Boolean) false;
		if (cls == char.class || cls == Character.class)
			return (T) (Character) '\0';
		if (cls == byte.class || cls == Byte.class)
			return (T) (Byte) (byte) 0;
		if (cls == short.class || cls == Short.class)
			return (T) (Short) (short) 0;
		if (cls == int.class || cls == Integer.class)
			return (T) (Integer) 0;
		if (cls == long.class || cls == Long.class)
			return (T) (Long) (long) 0;
		if (cls == float.class || cls == Float.class)
			return (T) (Float) (float) 0;
		if (cls == double.class || cls == Double.class)
			return (T) (Double) (double) 0;
		if (cls == String.class)
			return (T) "";
		if (cls.isArray())
			return (T) Array.newInstance(cls.getComponentType(), 0);

		return null;
	}
	
	/**
	 * 将字节流以一个易于观察的方式打成字符串
	 * 
	 * @param bytes
	 *            字节流
	 * @param charset
	 *            字节的编码
	 * @return 字节流的一个易于观察的表现形式
	 */
	public static String PrintHex(byte[] bytes, Charset charset) {
		if (bytes == null)
			bytes = ArrayUtils.EMPTY_BYTE_ARRAY;

		StringBuffer buffer = new StringBuffer("ADDR1 |  0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F |  D A T A [" + bytes.length + "]" + SystemUtils.LINE_SEPARATOR);

		int lineCount = bytes.length % 16 == 0 ? bytes.length / 16 : bytes.length / 16 + 1;
		for (int i = 0; i < lineCount; i++) {
			int count = i == lineCount - 1 ? bytes.length % 16 == 0 ? 16 : bytes.length % 16 : 16;

			String addr = StringUtils.leftPad(Integer.toHexString(i).toUpperCase(), 3, '0') + "0";
			StringBuffer body = new StringBuffer(48);
			String data;
			try {
				data = new String(bytes, 16 * i, count, charset.name());
			} catch (UnsupportedEncodingException ex) {
				throw new PaymentException(CommonErr.UnsupportedEncoding, charset.name());
			}

			for (int j = 0; j < count; j++) {
				byte b = bytes[i * 16 + j];

				int m = b >> 4 & 0x0F;
				int n = b & 0x0F;

				body.append(" ");
				body.append((char) (m > 9 ? 'A' + m - 10 : '0' + m));
				body.append((char) (n > 9 ? 'A' + n - 10 : '0' + n));
			}

			StringBuffer dataBuffer = new StringBuffer();
			for (int j = 0; j < data.length(); j++)
				dataBuffer.append(data.charAt(j) < 32 ? '.' : data.charAt(j));

			buffer.append(addr).append(" |");
			buffer.append(StringUtils.rightPad(body.toString(), 48)).append(" | ");
			buffer.append(dataBuffer.toString()).append(SystemUtils.LINE_SEPARATOR);
		}

		return buffer.toString();
	}
}