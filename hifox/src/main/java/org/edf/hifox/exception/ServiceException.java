package org.edf.hifox.exception;

/**
 * 
 * @author WangYang
 *
 */
public abstract class ServiceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1601554127888264236L;

	/**
	 * 获取异常代码
	 * 
	 * @return 异常代码
	 */
	public abstract String getCode();
	
	/**
	 * 获取异常参数
	 * 
	 * @return 获取异常参数
	 */
	public abstract Object[] getParameters();
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}
