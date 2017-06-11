package org.edf.hifox.core.processor;

/**
 * 
 * @author WangYang
 *
 */
public interface Processor<V, P> {

	V process(P data);

}
