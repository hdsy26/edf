package org.edf.hifox.converter.creator;

import java.util.List;

import org.edf.hifox.converter.rule.XsRule;
import org.edf.hifox.register.converter.registry.xmlbean.ClassDef;
import org.edf.hifox.register.converter.registry.xmlbean.ConverterMapping;
import org.edf.hifox.register.converter.registry.xmlbean.FieldDef;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

/**
 * 
 * @author WangYang
 *
 */
public class XsRuleCreator implements RuleCreator<XsRule> {
	
	@Override
	public XsRule create(ConverterMapping<?, ?, XsRule> meta) {
		XStream parser = new XStream(new Xpp3Driver(new NoNameCoder()));
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
		return new XsRule(parser);
	}
	
}
