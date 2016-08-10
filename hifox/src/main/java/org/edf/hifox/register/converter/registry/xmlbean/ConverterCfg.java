package org.edf.hifox.register.converter.registry.xmlbean;

import java.util.ArrayList;
import java.util.List;

import org.edf.hifox.converter.rule.Rule;

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
