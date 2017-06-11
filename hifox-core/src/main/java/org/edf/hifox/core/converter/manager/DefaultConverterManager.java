package org.edf.hifox.core.converter.manager;

import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.converter.Converter;
import org.edf.hifox.core.converter.rule.Rule;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.register.MetaRegistry;
import org.edf.hifox.core.register.converter.registry.xmlbean.ConverterMapping;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultConverterManager implements ConverterManager<Object, Object> {
	
	private MetaRegistry<ConverterMapping<Object, Object, Rule>> registry;

	public void setRegistry(
			MetaRegistry<ConverterMapping<Object, Object, Rule>> registry) {
		this.registry = registry;
	}

	@Override
	public Object convert(String mappingId, Object source) {
		ConverterMapping<Object, Object, Rule> mapping = registry.getMeta(mappingId);
		if(mapping == null)
			throw new FailureException(ErrorCodeConstant.E0001S003, new Object[]{mappingId});
		Converter<Object, Object, Rule> converter = mapping.getConverter();
		Object result = converter.convert(source, mapping.getRule());
		return result;
	}
	
}
