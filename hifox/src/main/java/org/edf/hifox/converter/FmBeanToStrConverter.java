package org.edf.hifox.converter;

import java.io.StringWriter;

import org.edf.hifox.converter.rule.FmRule;

import freemarker.template.Template;

/**
 * 
 * @author WangYang
 *
 */
public class FmBeanToStrConverter implements Converter<String, Object, FmRule> {
	
	@Override
	public String convert(Object data, FmRule rule) {
		Template core = rule.getCore();
		StringWriter sw = new StringWriter();
		try {
			core.process(data, sw);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return sw.toString();
	}
	
}
