package org.edf.hifox.converter;

import org.edf.hifox.converter.rule.XsRule;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author WangYang
 *
 */
public class XsXmlToBeanConverter implements Converter<Object, String, XsRule> {
	
	@Override
	public Object convert(String data, XsRule rule) {
		XStream core = rule.getCore();
		return core.fromXML(data);
	}
	
}
