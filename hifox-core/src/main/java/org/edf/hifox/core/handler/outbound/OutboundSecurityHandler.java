package org.edf.hifox.core.handler.outbound;

import java.util.Map;

import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.datatransfer.Message;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.reqinfo.OutboundRequestInfo;

/**
 * 
 * @author WangYang
 *
 */
public class OutboundSecurityHandler implements Handler<OutboundRequestInfo> {

	@Override
	public void handle(OutboundRequestInfo data, Invocation invocation) {
		if(data.getRequestMessage() instanceof Message) {
			messageHandle(data, invocation);
		} else if(data.getRequestMessage() instanceof Map) {
			mapHandle(data, invocation);
		} else {
			stringHandle(data, invocation);
		}
	}
	
	private void messageHandle(OutboundRequestInfo data, Invocation invocation) {
		invocation.invoke(data);
	}
	
	private void mapHandle(OutboundRequestInfo data, Invocation invocation) {
		invocation.invoke(data);
	}
	
	private void stringHandle(OutboundRequestInfo data, Invocation invocation) {
		invocation.invoke(data);
	}

}
