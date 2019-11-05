package com.qhgctech.payment.exception;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qhgctech.payment.protocol.utils.RuntimeUtils;

public class PaymentException extends RuntimeException{
	private static final long serialVersionUID = -4742498093912068865L;
	private static final Log log = LogFactory.getLog(PaymentException.class);
	/**
	 * 错误码
	 */
	public final String errCode;

	/**
	 * 初始化一个内部异常
	 * 
	 * @param err
	 *            错误码
	 */
	public PaymentException(ErrCode err) {
		super(buildExString(err, null, null));

		this.errCode = err.code;
	}

	/**
	 * 初始化一个内部异常
	 * 
	 * @param err
	 *            错误码
	 * @param info
	 *            附加信息
	 */
	public PaymentException(ErrCode err, String info) {
		super(buildExString(err, info, null));

		this.errCode = err.code;
	}

	/**
	 * 初始化一个内部异常
	 * 
	 * @param err
	 *            错误码
	 * @param innerEx
	 *            引发该异常的异常
	 */
	public PaymentException(ErrCode err, Throwable innerEx) {
		super(buildExString(err, null, innerEx));

		this.errCode = err.code;
	}

	/**
	 * 初始化一个内部异常
	 * 
	 * @param err
	 *            错误码
	 * @param info
	 *            附加信息
	 * @param innerEx
	 *            引发该异常的异常
	 */
	public PaymentException(ErrCode err, String info, Throwable innerEx) {
		super(buildExString(err, info, innerEx));

		this.errCode = err.code;
	}

	/**
	 * 工具函数 用于生成异常的描述信息
	 */
	private static String buildExString(ErrCode err, String msg, Throwable innerEx) {
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[").append(err.code).append("] ").append(err.desc);

			if (msg != null && msg.length() != 0)
				buffer.append(" - ").append(msg);

			if (innerEx != null)
				if (innerEx instanceof PaymentException)
					buffer.append(SystemUtils.LINE_SEPARATOR).append(">> ").append(innerEx.getMessage());
				else
					buffer.append(listExceptionCauseChain(innerEx));

			return buffer.toString();
		} catch (Exception ex) {
			throw new UnsupportedOperationException("err==null");
		}
	}

	/**
	 * 工具函数 用于生成异常的cause链
	 */
	private static String listExceptionCauseChain(Throwable ex) {
		if (log.isDebugEnabled())
			return SystemUtils.LINE_SEPARATOR + ">> " + RuntimeUtils.PrintEx(ex);

		StringBuffer buffer = new StringBuffer();
		do {
			buffer.append(SystemUtils.LINE_SEPARATOR).append(">> ");
			buffer.append(ex.getClass().getName());
			if (ex.getMessage() != null && ex.getMessage().length() != 0)
				buffer.append(": ").append(ex.getMessage());
		} while ((ex = ex.getCause()) != null);

		return buffer.toString();
	}

	@Override
	public String toString() {
		return this.getMessage();
	}
}
