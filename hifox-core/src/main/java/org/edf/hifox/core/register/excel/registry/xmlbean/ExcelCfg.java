package org.edf.hifox.core.register.excel.registry.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author WangYang
 *
 */
public class ExcelCfg {
	private List<ExcelDef> excelDefs = new ArrayList<ExcelDef>();

	public List<ExcelDef> getExcelDefs() {
		return excelDefs;
	}

	public void addExcelDef(ExcelDef excelDef) {
		this.excelDefs.add(excelDef);
	}
}
