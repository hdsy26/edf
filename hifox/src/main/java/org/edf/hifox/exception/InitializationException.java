package org.edf.hifox.exception;

/**
 * 
 * @author WangYang
 *
 */
public class InitializationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7842425974621931327L;
	
	public InitializationException() {
		super();
	}

	public InitializationException(String message) {
		super(message);
	}

	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitializationException(Throwable cause) {
		super(cause);
	}
	
}
