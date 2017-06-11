package org.edf.hifox.core.register.converter;

import java.nio.charset.Charset;
import java.util.List;

import org.edf.hifox.core.constant.ConvertConstant;
import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.converter.Converter;
import org.edf.hifox.core.converter.creator.RuleCreator;
import org.edf.hifox.core.converter.rule.Rule;
import org.edf.hifox.core.exception.InitializationException;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;
import org.edf.hifox.core.parser.Parser;
import org.edf.hifox.core.register.AbstractRegister;
import org.edf.hifox.core.register.converter.registry.ConverterRegistry;
import org.edf.hifox.core.register.converter.registry.xmlbean.ClassDef;
import org.edf.hifox.core.register.converter.registry.xmlbean.ConverterCfg;
import org.edf.hifox.core.register.converter.registry.xmlbean.ConverterMapping;
import org.edf.hifox.core.register.converter.registry.xmlbean.TemplateDef;
import org.edf.hifox.core.util.StreamUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 
 * @author WangYang
 *
 */
public class ConverterConfigRegister extends AbstractRegister {
	
	private static final Logger logger = LoggerFactory.getLogger(ConverterConfigRegister.class);
	
	private Parser<ConverterCfg> parser;
	private ConverterRegistry registry;
	
	public void setParser(Parser<ConverterCfg> parser) {
		this.parser = parser;
	}

	public void setRegistry(ConverterRegistry registry) {
		this.registry = registry;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void regist(Resource[] resources) throws Exception {
		logger.info(LogCodeConstant.REG00001);
		ConverterCfg result;
		for(Resource resource : resources) {
			logger.info(LogCodeConstant.REG00002, new Object[]{resource.getURI()});
			result = parser.parse(resource);
			Converter converter;
			RuleCreator<Rule> ruleCreator;
			Rule rule;
			TemplateDef templateDef;
			List<ClassDef> classDefs;
			for(ConverterMapping<?, ?, Rule> mapping : result.getMappings()) {
				classDefs = mapping.getClassDefs();
				templateDef = mapping.getTemplateDef();
				if((classDefs != null && templateDef != null) || 
						(classDefs == null && templateDef == null))
					throw new InitializationException("configuration error!");
				if(templateDef != null) {
					if(templateDef.getEmbed() != null) {
						ClassPathResource res = new ClassPathResource(templateDef.getEmbed());
						String encoding = templateDef.getEncoding();
						if(encoding == null)
							encoding = Charset.defaultCharset().name();
						String embedStr = StreamUtil.readStream(res.getInputStream(), templateDef.getEncoding(), true);
						templateDef.setValue(embedStr.replace(ConvertConstant.EMBED_SIGN, templateDef.getValue()));
					}
				}
				
				converter = this.getBean(mapping.getConverterId(), Converter.class);
				ruleCreator = this.getBean(mapping.getRuleCreatorId(), RuleCreator.class);
				rule = ruleCreator.create(mapping);
				mapping.setConverter(converter);
				mapping.setRuleCreator(ruleCreator);
				mapping.setRule(rule);
				registry.addUniqueMeta(mapping.getId(), mapping);
			}
		}
		
	}
	
}
