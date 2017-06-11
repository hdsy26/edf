package org.edf.hifox.core.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.exception.FailureException;

public class ThreadPoolExecutor implements ExecutorService {
	
	private java.util.concurrent.ThreadPoolExecutor threadPoolExecutor;
	
	private int corePoolSize;
	private int maximumPoolSize;
	private long keepAliveTime;
	private boolean allowCoreThreadTimeOut = false;
	private BlockingQueue<Runnable> workQueue;
	private RejectedExecutionHandler policy;
	
	private boolean allowShutdown = true;
	private boolean allowShutdownNow = true;
	
	public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime,
			BlockingQueue<Runnable> workQueue) {
		super();
		this.corePoolSize = corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.keepAliveTime = keepAliveTime;
		this.workQueue = workQueue;
		
		this.threadPoolExecutor = new java.util.concurrent.ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.MILLISECONDS,
				workQueue, new AbortPolicy());
	}
	
	public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime,
			BlockingQueue<Runnable> workQueue,
			boolean allowShutdown,
			boolean allowShutdownNow) {
		super();
		this.corePoolSize = corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.keepAliveTime = keepAliveTime;
		this.workQueue = workQueue;
		this.allowShutdown = allowShutdown;
		this.allowShutdownNow = allowShutdownNow;
		
		this.threadPoolExecutor = new java.util.concurrent.ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.MILLISECONDS,
				workQueue, new AbortPolicy());
	}

	public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime,
			BlockingQueue<Runnable> workQueue,
			RejectedExecutionHandler policy,
			boolean allowShutdown,
			boolean allowShutdownNow) {
		super();
		this.corePoolSize = corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.keepAliveTime = keepAliveTime;
		this.workQueue = workQueue;
		this.policy = policy;
		this.allowShutdown = allowShutdown;
		this.allowShutdownNow = allowShutdownNow;
		
		this.threadPoolExecutor = new java.util.concurrent.ThreadPoolExecutor(
				corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.MILLISECONDS,
				workQueue, policy);
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}
	public long getKeepAliveTime() {
		return keepAliveTime;
	}
	public boolean isAllowCoreThreadTimeOut() {
		return allowCoreThreadTimeOut;
	}
	public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
		this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
		this.threadPoolExecutor.allowCoreThreadTimeOut(allowCoreThreadTimeOut);
	}
	public BlockingQueue<Runnable> getWorkQueue() {
		return workQueue;
	}
	public RejectedExecutionHandler getPolicy() {
		return policy;
	}
	public boolean isAllowShutdown() {
		return allowShutdown;
	}
	public boolean isAllowShutdownNow() {
		return allowShutdownNow;
	}


	@Override
	public void execute(Runnable command) {
		threadPoolExecutor.execute(command);
	}

	@Override
	public void shutdown() {
		threadPoolExecutor.shutdown();
	}

	@Override
	public List<Runnable> shutdownNow() {
		if(!allowShutdownNow)
			throw new FailureException(ErrorCodeConstant.E0001S050);
		return threadPoolExecutor.shutdownNow();
	}

	@Override
	public boolean isShutdown() {
		if(!allowShutdown)
			throw new FailureException(ErrorCodeConstant.E0001S051);
		return threadPoolExecutor.isShutdown();
	}

	@Override
	public boolean isTerminated() {
		return threadPoolExecutor.isTerminated();
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return threadPoolExecutor.awaitTermination(timeout, unit);
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return threadPoolExecutor.submit(task);
	}

	@Override
	public <T> Future<T> submit(Runnable task, T result) {
		return threadPoolExecutor.submit(task, result);
	}

	@Override
	public Future<?> submit(Runnable task) {
		return threadPoolExecutor.submit(task);
	}

	@Override
	public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
			throws InterruptedException {
		return threadPoolExecutor.invokeAll(tasks);
	}

	@Override
	public <T> List<Future<T>> invokeAll(
			Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
			throws InterruptedException {
		return threadPoolExecutor.invokeAll(tasks, timeout, unit);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
			throws InterruptedException, ExecutionException {
		return threadPoolExecutor.invokeAny(tasks);
	}

	@Override
	public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
			long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return threadPoolExecutor.invokeAny(tasks, timeout, unit);
	}

}
