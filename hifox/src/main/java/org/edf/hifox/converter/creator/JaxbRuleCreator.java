package org.edf.hifox.converter.creator;

import java.util.List;

import javax.xml.bind.JAXBContext;

import org.edf.hifox.converter.rule.JaxbRule;
import org.edf.hifox.register.converter.registry.xmlbean.ClassDef;
import org.edf.hifox.register.converter.registry.xmlbean.ConverterMapping;

/**
 * 
 * @author WangYang
 *
 */
public class JaxbRuleCreator implements RuleCreator<JaxbRule> {
	
	@Override
	public JaxbRule create(ConverterMapping<?, ?, JaxbRule> meta) {
		List<ClassDef> defs = meta.getClassDefs();
		JAXBContext context;
		try {
			Class<?>[] clazzs = new Class<?>[defs.size()];
			int i = 0;
			for(ClassDef def : defs) {
				clazzs[i] = Class.forName(def.getName());
				i++;
			}
			context = JAXBContext.newInstance(clazzs);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new JaxbRule(context);
	}
	
}
