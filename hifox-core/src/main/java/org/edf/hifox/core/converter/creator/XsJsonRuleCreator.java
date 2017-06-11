package org.edf.hifox.core.converter.creator;

import java.util.List;

import org.edf.hifox.core.converter.rule.XsJsonRule;
import org.edf.hifox.core.register.converter.registry.xmlbean.ClassDef;
import org.edf.hifox.core.register.converter.registry.xmlbean.ConverterMapping;
import org.edf.hifox.core.register.converter.registry.xmlbean.FieldDef;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * 
 * @author WangYang
 *
 */
public class XsJsonRuleCreator implements RuleCreator<XsJsonRule> {
	
	@Override
	public XsJsonRule create(ConverterMapping<?, ?, XsJsonRule> meta) {
		XStream parser = new XStream(new JettisonMappedXmlDriver());
		List<ClassDef> defs = meta.getClassDefs();
		try {
			for(ClassDef def : defs) {
				Class<?> type = Class.forName(def.getName());
				parser.processAnnotations(type);
				
				List<FieldDef> fieldDefs = def.getFieldDefs();
				if(fieldDefs != null)
					for(FieldDef item : fieldDefs) {
						parser.alias(item.getName(), Class.forName(item.getFieldType()), Class.forName(item.getFieldImpl()));
					}
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return new XsJsonRule(parser);
	}
	
}
