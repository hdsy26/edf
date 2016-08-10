package org.edf.hifox.parser;

import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultParser implements Parser<Object> {
	private Resource ruleResource;
	private String schemaLocation;
	private String className;

	public void setRuleResource(Resource ruleResource) {
		this.ruleResource = ruleResource;
	}
	public void setSchemaLocation(String schemaLocation) {
		this.schemaLocation = schemaLocation;
	}
	public void setClassName(String className) {
		this.className = className;
	}


	@Override
	public Object parse(Resource resource)  throws Exception {
		InputStream ruleis = null;
		InputStream resourceis = null;
		try {
			ruleis = ruleResource.getInputStream();
			Digester digester = DigesterLoader.createDigester(new InputSource(ruleis));
			if(schemaLocation != null) {
				ClassPathResource res = new ClassPathResource(schemaLocation);
				digester.setSchema(res.getURL().getPath());
				digester.setValidating(true);
			}
		    digester.setNamespaceAware(true);
		    
		    digester.setErrorHandler(new ErrorHandler() {
				@Override
				public void warning(SAXParseException exception) throws SAXException {
				}
				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					throw exception;
				}
				@Override
				public void error(SAXParseException exception) throws SAXException {
					throw exception;
				}
			});
		    
		    resourceis = resource.getInputStream();
	    	Object result = Class.forName(className).newInstance();
		    digester.push(result);
		    digester.parse(resourceis);
		    
		    return result;
		    
		} finally {
			if(ruleis != null)
				try {
					ruleis.close();
				} catch (Exception e) {
				}
			if(resourceis != null)
				try {
					resourceis.close();
				} catch (Exception e) {
				}
		}
	}
	
	
}
