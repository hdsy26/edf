package org.edf.hifox.processor;

/**
 * 
 * @author WangYang
 *
 */
public interface Processor<V, P> {

	V process(P data);

}
