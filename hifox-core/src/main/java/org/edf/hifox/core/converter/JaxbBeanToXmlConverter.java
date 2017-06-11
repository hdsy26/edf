package org.edf.hifox.core.converter;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.edf.hifox.core.converter.rule.JaxbRule;

/**
 * 
 * @author WangYang
 *
 */
public class JaxbBeanToXmlConverter implements Converter<String, Object, JaxbRule> {
	
	@Override
	public String convert(Object data, JaxbRule rule) {
		JAXBContext context = rule.getContext();
		StringWriter writer;
		try {
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			writer = new StringWriter();
			marshaller.marshal(data, writer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}
	
}
