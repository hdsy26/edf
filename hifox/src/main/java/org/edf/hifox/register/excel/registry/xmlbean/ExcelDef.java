package org.edf.hifox.register.excel.registry.xmlbean;

import java.util.List;

/**
 * 
 * @author WangYang
 *
 */
public class ExcelDef {
	private String id;
	private String sheetName;
	private List<Column> columns;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
}
