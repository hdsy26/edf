package org.edf.hifox.converter;

import org.edf.hifox.converter.rule.XsRule;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author WangYang
 *
 */
public class XsBeanToXmlConverter implements Converter<String, Object, XsRule> {
	
	@Override
	public String convert(Object data, XsRule rule) {
		XStream core = rule.getCore();
		return core.toXML(data);
	}
	
}
