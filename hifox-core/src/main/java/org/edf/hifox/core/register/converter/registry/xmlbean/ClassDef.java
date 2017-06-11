package org.edf.hifox.core.register.converter.registry.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author WangYang
 *
 */
public class ClassDef {
	
	private String name;
	private List<FieldDef> fieldDefs = new ArrayList<FieldDef>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<FieldDef> getFieldDefs() {
		return fieldDefs;
	}

	public void addFieldDef(FieldDef fieldDef) {
		this.fieldDefs.add(fieldDef);
	}
	
}
