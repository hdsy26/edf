package org.edf.hifox.core.handler.inbound;

import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.reqinfo.InboundRequestInfo;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class InboundSwapAreaHandler implements Handler<InboundRequestInfo> {
	
	private String eventTraceIdPath;
	private String reqUsernamePath;

	public void setEventTraceIdPath(String eventTraceIdPath) {
		this.eventTraceIdPath = eventTraceIdPath;
	}

	public void setReqUsernamePath(String reqUsernamePath) {
		this.reqUsernamePath = reqUsernamePath;
	}


	@Override
	public void handle(InboundRequestInfo data, Invocation invocation) {
		SwapAreaUtil.buildNewSwapArea();
		SwapAreaUtil.setInboundRequestInfo(data);
		SwapAreaUtil.setInboundRequestMessage(data.getRequestMessage());
		SwapAreaUtil.setInboundServiceId(data.getServiceId());
		
		String eventTraceId = SwapAreaUtil.getValue(eventTraceIdPath, String.class);
		SwapAreaUtil.setInboundEventTraceId(eventTraceId);
		
		String reqUsername = SwapAreaUtil.getValue(reqUsernamePath, String.class);
		SwapAreaUtil.setInboundReqUsername(reqUsername);
		
		invocation.invoke(data);
	}

}
