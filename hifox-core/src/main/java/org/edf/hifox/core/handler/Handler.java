package org.edf.hifox.core.handler;

import org.edf.hifox.core.chain.invocation.Invocation;

/**
 * 
 * @author WangYang
 *
 */
public interface Handler<E> {
	void handle(E data, Invocation invocation);
}
