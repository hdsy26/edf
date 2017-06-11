package org.edf.hifox.core.converter.creator;

import org.edf.hifox.core.converter.rule.Rule;
import org.edf.hifox.core.register.converter.registry.xmlbean.ConverterMapping;

/**
 * 
 * @author WangYang
 *
 */
public interface RuleCreator<R extends Rule> {
	R create(ConverterMapping<?, ?, R> mate);
}
