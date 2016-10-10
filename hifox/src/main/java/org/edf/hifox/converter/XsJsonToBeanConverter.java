package org.edf.hifox.converter;

import org.edf.hifox.converter.rule.XsJsonRule;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author WangYang
 *
 */
public class XsJsonToBeanConverter implements Converter<Object, String, XsJsonRule> {
	
	@Override
	public Object convert(String data, XsJsonRule rule) {
		XStream core = rule.getCore();
		return core.fromXML(data);
	}
	
}
