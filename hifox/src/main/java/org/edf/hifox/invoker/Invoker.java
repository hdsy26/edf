package org.edf.hifox.invoker;

/**
 * 
 * @author WangYang
 *
 */
public interface Invoker<V, P> {
	V invoke(P data);
}
