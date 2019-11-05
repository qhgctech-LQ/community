package com.qhgctech.payment.protocol;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.apache.commons.lang.ArrayUtils;

import com.qhgctech.payment.exception.AdapterErr;
import com.qhgctech.payment.exception.PaymentException;
import com.qhgctech.payment.protocol.utils.InstanceCreater;
import com.qhgctech.payment.protocol.utils.RuntimeUtils;

/**
 * 工具类 用于帮助适配器装载配置
 * 
 * @author 
 * 
 */
public class ConfigHelper {
	/**
	 * 从字节流中读取配置并填充配置对象
	 * 
	 * @param bytes
	 *            适配器的配置字节流
	 * @param cls
	 *            适配器的配置对象的类型
	 * @return 适配器的配置对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T LoadConfig(HashMap<String, String> hashMap, Class<T> cls) {
		try {

			// 创建配置对象实例
			Object o = InstanceCreater.CreateInstance(cls);

			// 遍历配置对象的各个public域 将配置信息填进去
			Field[] fields = cls.getFields();
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				f.setAccessible(true);

				// 判断目标域是否是数组
				if (f.getType().isArray()) {
					// 目标域是数组

					Object array = Array.newInstance(f.getType().getComponentType(), 0);

					int id = 0;

					while (true) {
						String n = f.getName() + String.valueOf(id++);
						String str = hashMap.get(n);
						if (str == null)
							break;
						else
							array = ArrayUtils.add((Object[]) array, RuntimeUtils.FromString(str, f.getType().getComponentType()));
					}

					try {
						f.set(o, array);
					} catch (Exception ex) {
						throw new PaymentException(AdapterErr.FillConfigFail, "field: " + f.getName() + " value: " + ArrayUtils.toString(array));
					}
				} else {
					// 目标域是普通域
					String str = null;
					try {
						str = hashMap.get(f.getName());
						if (str == null || str.length() == 0)
							f.set(o, RuntimeUtils.CreateDefaultValue(f.getType()));
						else
							f.set(o, RuntimeUtils.FromString(str, f.getType()));
					} catch (Exception ex) {
						throw new PaymentException(AdapterErr.FillConfigFail, "field: " + f.getName() + " value: " + str);
					}
				}
			}
			return (T) o;
		} catch (Exception ex) {
			throw new PaymentException(AdapterErr.LoadConfigFail, ex);
		}
	}
}
