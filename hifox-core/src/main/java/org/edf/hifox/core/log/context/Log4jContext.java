package org.edf.hifox.core.log.context;

import org.edf.hifox.core.util.UniqueCodeUtil;
import org.slf4j.MDC;

/**
 * 
 * @author WangYang
 *
 */
public class Log4jContext implements LogContext {

	@Override
	public void putContext(String key, String value) {
		if(value == null)
			return;
		MDC.put(key, value);
	}

	@Override
	public void removeContext(String key) {
		MDC.remove(key);
	}

	@Override
	public void putLogId() {
		MDC.put("['_logid']", UniqueCodeUtil.randomUUID());
	}

	@Override
	public void removeLogId() {
		MDC.remove("['_logid']");
	}

}
