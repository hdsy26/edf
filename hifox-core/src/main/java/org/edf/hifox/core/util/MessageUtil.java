package org.edf.hifox.core.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.edf.hifox.core.constant.FormatConstant;
import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.datatransfer.Head;
import org.edf.hifox.core.datatransfer.Message;
import org.edf.hifox.core.datatransfer.support.RequestHead;

/**
 * 
 * @author WangYang
 *
 */
public final class MessageUtil {
	
	private static String nodeId;
	
	private MessageUtil() {
		
	}

	public static String getNodeId() {
		return nodeId;
	}

	public static void setNodeId(String nodeId) {
		MessageUtil.nodeId = nodeId;
	}
	

	public static RequestHead createRequestHead(String sysServiceId, String language, String country, String username, boolean isContext) {
		RequestHead head =  new RequestHead();
		head.setSysReqNodeId(nodeId);
		head.setSysServiceId(sysServiceId);
		head.setSysServiceType("01");
		head.setSysEventTraceId(isContext ? SwapAreaUtil.getInboundEventTraceId() : UniqueCodeUtil.randomUUID());
		head.setSysReqVersion("1.0");
		head.setSysReqDatetime(DateFormatUtils.format(new Date(), FormatConstant.YMDHMSS));
		head.setSysLanguage(language);
		head.setSysCountry(country);
		head.setSysReqUsername(username);
		return head;
	}
	
	public static RequestHead createRequestHead(String sysServiceId) {
		return createRequestHead(sysServiceId, SwapAreaUtil.getOutboundLanguage(), 
				SwapAreaUtil.getOutboundCountry(), SwapAreaUtil.getOutboundReqUsername(), false);
	}
	
	public static RequestHead createRequestHead(String sysServiceId, boolean isContext) {
		return createRequestHead(sysServiceId, SwapAreaUtil.getOutboundLanguage(), 
				SwapAreaUtil.getOutboundCountry(), SwapAreaUtil.getOutboundReqUsername(), isContext);
	}
	
	@SuppressWarnings("unchecked")
	public static RequestHead getRequestHead() {
		Message<Head, Body> reqMsg = (Message<Head, Body>)SwapAreaUtil.getInboundRequestMessage();
		
		if(reqMsg == null) {
			RequestHead head = new RequestHead();
			head.setSysReqNodeId("");
			head.setSysTargetNodeId("");
			head.setSysEventTraceId("");
			head.setSysReqDatetime("");
			head.setSysServiceId("");
			head.setSysServiceType("");
			
			return head;
		}
		
		return (RequestHead) reqMsg.getHead();
	}
	
}
