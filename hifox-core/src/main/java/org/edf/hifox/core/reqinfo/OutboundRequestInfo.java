package org.edf.hifox.core.reqinfo;

/**
 * 
 * @author WangYang
 *
 */
public class OutboundRequestInfo {
	private String contentString;
	private Object requestMessage;
	
	private String serviceId;
	
	private boolean checkStatus;
	
	public String getContentString() {
		return contentString;
	}
	public void setContentString(String contentString) {
		this.contentString = contentString;
	}
	public Object getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(Object requestMessage) {
		this.requestMessage = requestMessage;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public boolean isCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}
