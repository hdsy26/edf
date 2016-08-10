package org.edf.hifox.register.channel.registry.xmlbean;

import org.edf.hifox.channel.Channel;

/**
 * 
 * @author WangYang
 *
 */
public class ChannelDef {
	private String id;
	private String type;
	private Integer retryCount = 0;
	private Integer delayTime = 0;
	
	private HttpEndpointDef httpEndpointDef;
	
	private Channel channel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Integer getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}

	public HttpEndpointDef getHttpEndpointDef() {
		return httpEndpointDef;
	}

	public void setHttpEndpointDef(HttpEndpointDef httpEndpointDef) {
		this.httpEndpointDef = httpEndpointDef;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
