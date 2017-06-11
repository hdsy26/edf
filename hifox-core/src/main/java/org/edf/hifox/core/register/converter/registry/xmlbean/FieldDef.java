package org.edf.hifox.core.register.converter.registry.xmlbean;

/**
 * 
 * @author WangYang
 *
 */
public class FieldDef {
	private String name;
	private String fieldType;
	private String fieldImpl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldImpl() {
		return fieldImpl;
	}
	public void setFieldImpl(String fieldImpl) {
		this.fieldImpl = fieldImpl;
	}
}
