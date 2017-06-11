package org.edf.hifox.core.concurrent.policy;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;

public class DiscardPolicy implements RejectedExecutionHandler {
	private static final Logger logger = LoggerFactory.getLogger(DiscardPolicy.class);
	
	private static DiscardPolicy policy = new DiscardPolicy();
	
	private DiscardPolicy() {
		super();
	}
	
	public static DiscardPolicy getInstance() {
		return policy;
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logger.info(LogCodeConstant.SYS00021, new Object[]{r.toString()});
	}

}
