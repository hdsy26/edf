package org.edf.hifox.core.channel;

import java.util.Map;

/**
 * 
 * @author WangYang
 *
 */
public interface Channel {
	String sendMsg(String serviceId, String reqMsg);
	String sendMsg(String serviceId, Map<String, String> reqMsg);
}
