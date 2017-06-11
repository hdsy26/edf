package org.edf.hifox.core.exception;

import org.edf.hifox.core.util.SpringContextUtil;

/**
 * 
 * @author WangYang
 *
 */
public class UncertainException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271689233756905882L;

	private String errorCode;
	private Object[] params;
	private String message;
	
	
	public UncertainException() {
		super();
	}
	
	public UncertainException(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.message = SpringContextUtil.getMessage(errorCode);
	}

	public UncertainException(String errorCode, Object[] params) {
		super();
		this.errorCode = errorCode;
		this.params = params;
		this.message = SpringContextUtil.getMessage(errorCode, params);
	}
	
	public UncertainException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.message = SpringContextUtil.getMessage(errorCode);
	}
	
	public UncertainException(String errorCode, Object[] params, Throwable cause) {
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
