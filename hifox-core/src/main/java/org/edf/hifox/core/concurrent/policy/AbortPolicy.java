package org.edf.hifox.core.concurrent.policy;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;

public class AbortPolicy implements RejectedExecutionHandler {
	private static final Logger logger = LoggerFactory.getLogger(AbortPolicy.class);
	
	private static AbortPolicy policy = new AbortPolicy();
	
	private AbortPolicy() {
		super();
	}
	
	public static AbortPolicy getInstance() {
		return policy;
	}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logger.info(LogCodeConstant.SYS00020, new Object[]{r.toString()});
		throw new FailureException();
	}

}
