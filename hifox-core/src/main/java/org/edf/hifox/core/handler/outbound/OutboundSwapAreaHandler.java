package org.edf.hifox.core.handler.outbound;

import java.util.Map;

import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.datatransfer.Head;
import org.edf.hifox.core.datatransfer.Message;
import org.edf.hifox.core.esb.ServiceDirectory;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.reqinfo.OutboundRequestInfo;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class OutboundSwapAreaHandler implements Handler<OutboundRequestInfo> {
	
	private String reqUsernamePath;
	
	private ServiceDirectory<Map<String, String>> serviceDirectory;

	public void setReqUsernamePath(String reqUsernamePath) {
		this.reqUsernamePath = reqUsernamePath;
	}

	public void setServiceDirectory(
			ServiceDirectory<Map<String, String>> serviceDirectory) {
		this.serviceDirectory = serviceDirectory;
	}


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
		SwapAreaUtil.setOutboundRequestInfo(data);
		SwapAreaUtil.setOutboundRequestMessage((Message<Head, Body>)data.getRequestMessage());
		String reqUsername = SwapAreaUtil.getValue(reqUsernamePath, String.class);
		String serviceId = data.getServiceId();
		
		SwapAreaUtil.setOutboundServiceId(serviceId);
		SwapAreaUtil.setOutboundReqUsername(reqUsername);
		SwapAreaUtil.setOutboundServiceDirItem(serviceDirectory.lookup(serviceId));
		
		invocation.invoke(data);
	}
	
	private void mapHandle(OutboundRequestInfo data, Invocation invocation) {
		String serviceId = data.getServiceId();
		
		SwapAreaUtil.setOutboundRequestInfo(data);
		SwapAreaUtil.setOutboundServiceId(serviceId);
		SwapAreaUtil.setOutboundServiceDirItem(serviceDirectory.lookup(serviceId));
		
		invocation.invoke(data);
	}
	
	private void stringHandle(OutboundRequestInfo data, Invocation invocation) {
		String serviceId = data.getServiceId();
		
		SwapAreaUtil.setOutboundRequestInfo(data);
		SwapAreaUtil.setOutboundServiceId(serviceId);
		SwapAreaUtil.setOutboundServiceDirItem(serviceDirectory.lookup(serviceId));
		
		invocation.invoke(data);
	}

}
