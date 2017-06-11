package org.edf.hifox.core.handler.inbound;

import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.reqinfo.InboundRequestInfo;

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
