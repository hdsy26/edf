package org.edf.hifox.converter.creator;

import java.io.IOException;

import org.edf.hifox.converter.rule.FmRule;
import org.edf.hifox.register.converter.registry.xmlbean.ConverterMapping;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author WangYang
 *
 */
public class FmRuleCreator implements RuleCreator<FmRule> {

	@Override
	public FmRule create(ConverterMapping<?, ?, FmRule> meta) {
		Configuration cfg = new Configuration();
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate(meta.getId(), meta.getTemplateDef().getValue());
		cfg.setTemplateLoader(stringLoader);
		try {
			Template temp = cfg.getTemplate(meta.getId());
			return new FmRule(temp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
