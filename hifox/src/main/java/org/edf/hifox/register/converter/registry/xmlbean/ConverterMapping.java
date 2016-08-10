package org.edf.hifox.register.converter.registry.xmlbean;

import java.util.List;

import org.edf.hifox.converter.Converter;
import org.edf.hifox.converter.creator.RuleCreator;
import org.edf.hifox.converter.rule.Rule;

/**
 * 
 * @author WangYang
 *
 */
public class ConverterMapping<V, D, R extends Rule> {
	
	private String id;
	
	private String converterId;
	private Converter<V, D, R> converter;
	
	private String ruleCreatorId;
	private RuleCreator<R> ruleCreator;
	
	private R rule;
	
	private TemplateDef templateDef;
	private List<ClassDef> classDefs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConverterId() {
		return converterId;
	}
	public void setConverterId(String converterId) {
		this.converterId = converterId;
	}
	public Converter<V, D, R> getConverter() {
		return converter;
	}
	public void setConverter(Converter<V, D, R> converter) {
		this.converter = converter;
	}
	public String getRuleCreatorId() {
		return ruleCreatorId;
	}
	public void setRuleCreatorId(String ruleCreatorId) {
		this.ruleCreatorId = ruleCreatorId;
	}
	public RuleCreator<R> getRuleCreator() {
		return ruleCreator;
	}
	public void setRuleCreator(RuleCreator<R> ruleCreator) {
		this.ruleCreator = ruleCreator;
	}
	public R getRule() {
		return rule;
	}
	public void setRule(R rule) {
		this.rule = rule;
	}
	public TemplateDef getTemplateDef() {
		return templateDef;
	}
	public void setTemplateDef(TemplateDef templateDef) {
		this.templateDef = templateDef;
	}
	public List<ClassDef> getClassDefs() {
		return classDefs;
	}
	public void setClassDefs(List<ClassDef> classDefs) {
		this.classDefs = classDefs;
	}
	
}
