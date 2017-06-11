package org.edf.hifox.core.util;

import java.util.Map;

import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.datatransfer.support.RequestMessage;
import org.edf.hifox.core.processor.Processor;
import org.edf.hifox.core.reqinfo.OutboundRequestInfo;

/**
 * 
 * @author WangYang
 *
 */
public final class OutboundUtil {
	private static Processor<Object, OutboundRequestInfo> processor;
	
	private OutboundUtil() {
		
	}
	
	public void setProcessor(Processor<Object, OutboundRequestInfo> processor) {
		OutboundUtil.processor = processor;
	}
	
	
	public static <T> T sendMsg(String serviceId, Body body) {
		return sendMsg(serviceId, body, true, false);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T sendMsg(String serviceId, Body body, boolean checkStatus, boolean isContext) {
		OutboundRequestInfo data = new OutboundRequestInfo();
		data.setServiceId(serviceId);
		RequestMessage reqMsg = new RequestMessage(MessageUtil.createRequestHead(serviceId, isContext));
		reqMsg.setBody(body);
		data.setRequestMessage(reqMsg);
		data.setCheckStatus(checkStatus);
		return (T)processor.process(data);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T sendMsg(String serviceId, Map<String, String> reqMsg) {
		OutboundRequestInfo data = new OutboundRequestInfo();
		data.setServiceId(serviceId);
		data.setRequestMessage(reqMsg);
		return (T)processor.process(data);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T sendMsg(String serviceId, String reqMsg) {
		OutboundRequestInfo data = new OutboundRequestInfo();
		data.setServiceId(serviceId);
		data.setRequestMessage(reqMsg);
		return (T)processor.process(data);
	}
	
}
