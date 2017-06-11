package org.edf.hifox.core.converter;

import org.edf.hifox.core.converter.rule.XsJsonRule;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author WangYang
 *
 */
public class XsBeanToJsonConverter implements Converter<String, Object, XsJsonRule> {
	
	@Override
	public String convert(Object data, XsJsonRule rule) {
		XStream core = rule.getCore();
		return core.toXML(data);
	}
	
}
