package org.edf.hifox.converter.rule;

import javax.xml.bind.JAXBContext;

/**
 * 
 * @author WangYang
 *
 */
public class JaxbRule implements Rule {
	
	private JAXBContext context;

	public JaxbRule(JAXBContext context) {
		super();
		this.context = context;
	}

	public JAXBContext getContext() {
		return context;
	}

	public void setContext(JAXBContext context) {
		this.context = context;
	}
	
}
