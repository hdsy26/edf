package org.edf.hifox.core.invoker;

/**
 * 
 * @author WangYang
 *
 */
public interface Invoker<V, P> {
	V invoke(P data);
}
