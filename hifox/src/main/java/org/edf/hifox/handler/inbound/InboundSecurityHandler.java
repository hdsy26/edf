package org.edf.hifox.handler.inbound;

import org.edf.hifox.chain.invocation.Invocation;
import org.edf.hifox.handler.Handler;
import org.edf.hifox.reqinfo.InboundRequestInfo;

/**
 * 
 * @author WangYang
 *
 */
public class InboundSecurityHandler implements Handler<InboundRequestInfo> {

	@Override
	public void handle(InboundRequestInfo data, Invocation invocation) {
		invocation.invoke(data);
	}

}
