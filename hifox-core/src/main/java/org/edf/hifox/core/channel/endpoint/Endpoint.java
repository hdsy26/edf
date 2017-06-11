package org.edf.hifox.core.channel.endpoint;

import java.util.Map;

/**
 * 
 * @author WangYang
 *
 */
public interface Endpoint {
	String send(String serviceId, String reqMsgStr);
	String send(String serviceId, Map<String, String> reqMsgStr);
}
