package org.edf.hifox.channel;

import java.util.Map;

import org.edf.hifox.channel.endpoint.Endpoint;
import org.edf.hifox.constant.LogCodeConstant;
import org.edf.hifox.log.Logger;
import org.edf.hifox.log.LoggerFactory;

/**
 * 
 * @author WangYang
 *
 */
public class HttpChannel implements Channel {
	private static final Logger logger = LoggerFactory.getLogger(HttpChannel.class);
	
	private Endpoint endpoint;
	private Integer retryCount;
	private Integer delayTime;

	public HttpChannel(Endpoint endpoint, Integer retryCount, Integer delayTime) {
		super();
		this.endpoint = endpoint;
		this.retryCount = retryCount;
		this.delayTime = delayTime;
	}

	@Override
	public String sendMsg(String serviceId, String reqMsgStr) {
		for(int i=0; i<=retryCount; i++) {
			try {
				return endpoint.send(serviceId, reqMsgStr);
			} catch (Exception e) {
				if(i == retryCount)
					throw new RuntimeException(e);
				
				logger.info(LogCodeConstant.SYS00014, new Object[]{delayTime});
				
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
				}
				
				logger.info(LogCodeConstant.SYS00015, new Object[]{i+1});
			}
		}
		return "";
	}

	@Override
	public String sendMsg(String serviceId, Map<String, String> reqMsg) {
		for(int i=0; i<=retryCount; i++) {
			try {
				return endpoint.send(serviceId, reqMsg);
			} catch (Exception e) {
				if(i == retryCount)
					throw new RuntimeException(e);
				
				logger.info(LogCodeConstant.SYS00014, new Object[]{delayTime});
				
				try {
					Thread.sleep(delayTime);
				} catch (InterruptedException e1) {
				}
				
				logger.info(LogCodeConstant.SYS00015, new Object[]{i+1});
			}
		}
		return "";
	}
	
}
