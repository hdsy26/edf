package org.edf.hifox.chain.invocation;

import java.util.Iterator;

import org.edf.hifox.handler.Handler;

/**
 * 
 * @author WangYang
 *
 */
public class ChainInvocation implements Invocation {

	private final Iterator<Handler<Object>> handlerIterator;

	public ChainInvocation(Iterator<Handler<Object>> handlerIterator) {
		super();
		this.handlerIterator = handlerIterator;
	}

	@Override
	public void invoke(Object data) {
		if (handlerIterator.hasNext()) {
			handlerIterator.next().handle(data, this);
		}
	}

}
