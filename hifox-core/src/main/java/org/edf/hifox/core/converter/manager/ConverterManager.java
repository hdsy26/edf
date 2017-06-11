package org.edf.hifox.core.converter.manager;

/**
 * 
 * @author WangYang
 *
 */
public interface ConverterManager<V, D> {
	V convert(String mappingId, D source);
}
