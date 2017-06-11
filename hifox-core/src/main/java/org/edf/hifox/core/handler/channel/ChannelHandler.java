package org.edf.hifox.core.handler.channel;

import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.invoker.Invoker;
import org.edf.hifox.core.reqinfo.OutboundRequestInfo;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class ChannelHandler implements Handler<OutboundRequestInfo> {
	
	private Invoker<String, OutboundRequestInfo> invoker;

	public void setInvoker(Invoker<String, OutboundRequestInfo> invoker) {
		this.invoker = invoker;
	}

	@Override
	public void handle(OutboundRequestInfo data, Invocation invocation) {
		String respMsgStr = invoker.invoke(data);
		SwapAreaUtil.setOutboundResponseMsgStr(respMsgStr);
		invocation.invoke(data);
	}

}
