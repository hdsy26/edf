package org.edf.hifox.exception;

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
	
	
	public UncertainException() {
		super();
	}
	
	public UncertainException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public UncertainException(String errorCode, Object[] params) {
		super();
		this.errorCode = errorCode;
		this.params = params;
	}
	
	public UncertainException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public UncertainException(String errorCode, Object[] params, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.params = params;
	}

	@Override
	public String getCode() {
		return errorCode;
	}

	@Override
	public Object[] getParameters() {
		return params;
	}
	
}
