package org.edf.hifox.handler.channel;

import org.edf.hifox.chain.invocation.Invocation;
import org.edf.hifox.handler.Handler;
import org.edf.hifox.invoker.Invoker;
import org.edf.hifox.reqinfo.OutboundRequestInfo;
import org.edf.hifox.util.SwapAreaUtil;

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
