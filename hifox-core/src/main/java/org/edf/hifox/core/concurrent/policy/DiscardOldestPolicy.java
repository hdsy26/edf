package org.edf.hifox.core.concurrent.policy;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;

public class DiscardOldestPolicy implements RejectedExecutionHandler {
	private static final Logger logger = LoggerFactory.getLogger(DiscardOldestPolicy.class);
	
	private static DiscardOldestPolicy policy = new DiscardOldestPolicy();
	
	private DiscardOldestPolicy() {
		super();
	}
	
	public static DiscardOldestPolicy getInstance() {
		return policy;
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logger.info(LogCodeConstant.SYS00022, new Object[]{r.toString()});
		if (!executor.isShutdown()) {
			executor.getQueue().poll();
			executor.execute(r);
        }
	}

}
