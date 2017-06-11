package org.edf.hifox.core.handler.outbound;

import java.util.Map;

import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.constant.ConvertConstant;
import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.datatransfer.Head;
import org.edf.hifox.core.datatransfer.Message;
import org.edf.hifox.core.datatransfer.support.RequestHead;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.reqinfo.OutboundRequestInfo;
import org.edf.hifox.core.util.DataConvertUtil;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class OutboundDataConvertHandler implements Handler<OutboundRequestInfo> {
	
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
	
	@SuppressWarnings("unchecked")
	private void messageHandle(OutboundRequestInfo data, Invocation invocation) {
		Message<Head, Body> reqMsg = (Message<Head, Body>)data.getRequestMessage();
		RequestHead head = ((RequestHead)reqMsg.getHead());
		head.setSysTargetNodeId(SwapAreaUtil.getOutboundServiceDirItem().get("targetNodeId"));
		String serviceId = head.getSysServiceId();
		String requestMessageStr = DataConvertUtil.convert(serviceId + ConvertConstant.REQ, data.getRequestMessage());
		data.setContentString(requestMessageStr);
		
		invocation.invoke(data);
		
		String respMsgStr = SwapAreaUtil.getOutboundResponseMsgStr();
		Message<Head, Body> respMsg = (Message<Head, Body>)DataConvertUtil.convert(serviceId + ConvertConstant.RESP, respMsgStr);
		SwapAreaUtil.setOutboundResponseMessage(respMsg);
	}
	
	private void mapHandle(OutboundRequestInfo data, Invocation invocation) {
		invocation.invoke(data);
	}
	
	private void stringHandle(OutboundRequestInfo data, Invocation invocation) {
		data.setContentString(data.getRequestMessage().toString());
		invocation.invoke(data);
	}

}
