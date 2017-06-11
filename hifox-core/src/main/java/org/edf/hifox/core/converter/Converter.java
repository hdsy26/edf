package org.edf.hifox.core.converter;

import org.edf.hifox.core.converter.rule.Rule;

/**
 * 
 * @author WangYang
 *
 */
public interface Converter<V, D, R extends Rule> {
	V convert(D data, R rule);
}
