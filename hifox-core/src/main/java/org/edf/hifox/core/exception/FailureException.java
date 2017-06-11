package org.edf.hifox.core.exception;

import org.edf.hifox.core.util.SpringContextUtil;

/**
 * 
 * @author WangYang
 *
 */
public class FailureException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271689233756905882L;

	private String errorCode;
	private Object[] params;
	private String message;
	
	
	public FailureException() {
		super();
	}
	
	public FailureException(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.message = SpringContextUtil.getMessage(errorCode);
	}

	public FailureException(String errorCode, Object[] params) {
		super();
		this.errorCode = errorCode;
		this.params = params;
		this.message = SpringContextUtil.getMessage(errorCode, params);
	}
	
	public FailureException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.message = SpringContextUtil.getMessage(errorCode);
	}
	
	public FailureException(String errorCode, Object[] params, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.params = params;
		this.message = SpringContextUtil.getMessage(errorCode, params);
	}

	@Override
	public String getCode() {
		return errorCode;
	}

	@Override
	public Object[] getParameters() {
		return params;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
