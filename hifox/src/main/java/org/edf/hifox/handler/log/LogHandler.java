package org.edf.hifox.handler.log;

import java.util.Map;
import java.util.Map.Entry;

import org.edf.hifox.chain.invocation.Invocation;
import org.edf.hifox.handler.Handler;
import org.edf.hifox.log.context.LogContext;
import org.edf.hifox.reqinfo.InboundRequestInfo;
import org.edf.hifox.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class LogHandler implements Handler<InboundRequestInfo> {

	private static Map<String, String> contexts;
	private static LogContext logContext;
	
	
	public void setContexts(Map<String, String> contexts) {
		LogHandler.contexts = contexts;
	}

	public void setLogContext(LogContext logContext) {
		LogHandler.logContext = logContext;
	}

	@Override
	public void handle(InboundRequestInfo data, Invocation invocation) {
		addLogContext();
		invocation.invoke(data);
	}

	public void addLogContext() {
		String value;
		for (Entry<String, String> context : contexts.entrySet()) {
			value = SwapAreaUtil.getValue(context.getValue(), String.class);
			if (value != null) {
				logContext.putContext(context.getKey(), value);
			}
		}
	}

	public static void removeLogContext() {
		for (String contextKey : contexts.keySet()) {
			logContext.removeContext(contextKey);
		}
	}

}
