package org.edf.hifox.chain;

import java.util.List;

import org.edf.hifox.chain.invocation.ChainInvocation;
import org.edf.hifox.handler.Handler;

/**
 * 
 * @author WangYang
 *
 */
public class HandlerChain implements Chain {
	private List<Handler<Object>> handlerList;

	public void setHandlerList(List<Handler<Object>> handlerList) {
		this.handlerList = handlerList;
	}

	@Override
	public void doChain(Object data) {
		new ChainInvocation(handlerList.iterator()).invoke(data);
	}
	
	
}
