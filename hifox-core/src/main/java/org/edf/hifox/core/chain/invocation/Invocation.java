package org.edf.hifox.core.chain.invocation;

/**
 * 
 * @author WangYang
 *
 */
public interface Invocation {
	void invoke(Object input);
	boolean hasNext();
}
