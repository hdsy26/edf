package org.edf.hifox.log;

/**
 * 
 * @author WangYang
 *
 */
public interface Logger {
	String getName();
	
	boolean isTraceEnabled();
	void trace(String code);
	void trace(String code, Object[] args);
	void trace(String code, Throwable t);
	void trace(String code, Object[] args, Throwable t);
	
	boolean isDebugEnabled();
	void debug(String code);
	void debug(String code, Object[] args);
	void debug(String code, Throwable t);
	void debug(String code, Object[] args, Throwable t);
	
	boolean isInfoEnabled();
	void info(String code);
	void info(String code, Object[] args);
	void info(String code, Throwable t);
	void info(String code, Object[] args, Throwable t);
	
	boolean isWarnEnabled();
	void warn(String code);
	void warn(String code, Object[] args);
	void warn(String code, Throwable t);
	void warn(String code, Object[] args, Throwable t);
	
	boolean isErrorEnabled();
	void error(String code);
	void error(String code, Object[] args);
	void error(String code, Throwable t);
	void error(String code, Object[] args, Throwable t);
	
}
