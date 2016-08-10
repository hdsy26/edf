package org.edf.hifox.converter.manager;

/**
 * 
 * @author WangYang
 *
 */
public interface ConverterManager<V, D> {
	V convert(String mappingId, D source);
}
