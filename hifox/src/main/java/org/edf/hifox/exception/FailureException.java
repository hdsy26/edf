package org.edf.hifox.exception;

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
	
	
	public FailureException() {
		super();
	}
	
	public FailureException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public FailureException(String errorCode, Object[] params) {
		super();
		this.errorCode = errorCode;
		this.params = params;
	}
	
	public FailureException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public FailureException(String errorCode, Object[] params, Throwable cause) {
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
