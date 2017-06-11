package org.edf.hifox.core.register.converter.registry.xmlbean;

import java.util.ArrayList;
import java.util.List;

import org.edf.hifox.core.converter.rule.Rule;

/**
 * 
 * @author WangYang
 *
 */
public class ConverterCfg {
	private List<ConverterMapping<?, ?, Rule>> mappings = new ArrayList<ConverterMapping<?, ?, Rule>>();

	public List<ConverterMapping<?, ?, Rule>> getMappings() {
		return mappings;
	}

	public void addMapping(ConverterMapping<?, ?, Rule> mapping) {
		mappings.add(mapping);
	}

}
