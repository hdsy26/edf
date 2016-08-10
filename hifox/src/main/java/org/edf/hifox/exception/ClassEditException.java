package org.edf.hifox.exception;

/**
 * 
 * @author WangYang
 *
 */
public class ClassEditException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3271689233756905882L;

	public ClassEditException() {
		super();
	}

	public ClassEditException(String message) {
		super(message);
	}

	public ClassEditException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClassEditException(Throwable cause) {
		super(cause);
	}
	
}
