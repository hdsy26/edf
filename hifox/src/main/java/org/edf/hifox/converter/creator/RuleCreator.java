package org.edf.hifox.converter.creator;

import org.edf.hifox.converter.rule.Rule;
import org.edf.hifox.register.converter.registry.xmlbean.ConverterMapping;

/**
 * 
 * @author WangYang
 *
 */
public interface RuleCreator<R extends Rule> {
	R create(ConverterMapping<?, ?, R> mate);
}
