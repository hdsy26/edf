package org.edf.hifox.converter;

import org.edf.hifox.converter.rule.Rule;

/**
 * 
 * @author WangYang
 *
 */
public interface Converter<V, D, R extends Rule> {
	V convert(D data, R rule);
}
