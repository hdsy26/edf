package org.edf.hifox.core.concurrent.policy;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;

public class CallerRunsPolicy implements RejectedExecutionHandler {
	private static final Logger logger = LoggerFactory.getLogger(CallerRunsPolicy.class);
	
	private static CallerRunsPolicy policy = new CallerRunsPolicy();
	
	private CallerRunsPolicy() {
		super();
	}
	
	public static CallerRunsPolicy getInstance() {
		return policy;
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logger.info(LogCodeConstant.SYS00023, new Object[]{r.toString()});
		if (!executor.isShutdown()) {
            r.run();
        }
	}

}
