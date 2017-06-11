package org.edf.hifox.core.converter;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.edf.hifox.core.converter.rule.JaxbRule;

/**
 * 
 * @author WangYang
 *
 */
public class JaxbXmlToBeanConverter implements Converter<Object, String, JaxbRule> {
	
	@Override
	public Object convert(String data, JaxbRule rule) {
		JAXBContext context = rule.getContext();
		Object bean;
		try {
			Unmarshaller unmarshaller = context.createUnmarshaller();
			bean = unmarshaller.unmarshal(new StringReader(data));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return bean;
	}
	
}
