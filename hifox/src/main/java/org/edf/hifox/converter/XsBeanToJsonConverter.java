package org.edf.hifox.converter;

import org.edf.hifox.converter.rule.XsJsonRule;

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
