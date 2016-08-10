package org.edf.hifox.handler;

import org.edf.hifox.chain.invocation.Invocation;

/**
 * 
 * @author WangYang
 *
 */
public interface Handler<E> {
	void handle(E data, Invocation invocation);
}
