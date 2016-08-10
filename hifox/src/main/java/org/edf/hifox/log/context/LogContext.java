package org.edf.hifox.log.context;

/**
 * 
 * @author WangYang
 *
 */
public interface LogContext {
	void putContext(String key, String value);
	void removeContext(String key);
	void putLogId();
	void removeLogId();
	
}
