package org.edf.hifox.core.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.PropertyUtils;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.constant.SysParamConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;
import org.edf.hifox.core.register.excel.registry.ExcelRegistry;
import org.edf.hifox.core.register.excel.registry.xmlbean.Column;
import org.edf.hifox.core.register.excel.registry.xmlbean.ExcelDef;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class JxlExportExcel {
	
	private static final Logger logger = LoggerFactory.getLogger(JxlExportExcel.class);
	
	private static ExcelRegistry registry;

	public void setRegistry(ExcelRegistry registry) {
		JxlExportExcel.registry = registry;
	}
	
	public static String getSheetName(String id) {
		ExcelDef def = registry.getMeta(id);
		if(def == null)
			def = SwapAreaUtil.getValue("['" + id + "']", ExcelDef.class);
		
		if(def == null)
			throw new FailureException(ErrorCodeConstant.E0001S004, new Object[]{id});
		
		return def.getSheetName();
	}
	

	public static void export(String id, String dir, String fileName, List<Object> listdata) throws Exception {
		ExcelDef def = registry.getMeta(id);
		if(def == null)
			def = SwapAreaUtil.getValue("['" + id + "']", ExcelDef.class);
		
		if(def == null)
			throw new FailureException(ErrorCodeConstant.E0001S004, new Object[]{id});
		
		FileOutputStream fos = null;
		WritableWorkbook wwb = null;
		try {
			File file = new File(dir);
			if(!file.exists())
				file.mkdirs();
			
			fos = new FileOutputStream(dir + SysParamConstant.LINE_SEPARATOR + fileName);
			wwb = Workbook.createWorkbook(fos);
			WritableSheet sheet = wwb.createSheet(def.getSheetName(), 0);
			
			createHead(id, sheet);
			
			int y = 1;
			for(Object item : listdata) {
				export(id, sheet, item, y);
				y++;
			}
			
		} catch (Exception e) {
			logger.error(LogCodeConstant.SYS00009, e);
			throw e;
		} finally {
			if(wwb != null)
				try {
					wwb.close();
				} catch (Exception e) {
				}
			if(fos != null)
				try {
					fos.close();
				} catch (IOException e) {
				}
		}
	}
	
	public static void createHead(String id, WritableSheet sheet) throws Exception {
		ExcelDef def = registry.getMeta(id);
		if(def == null)
			def = SwapAreaUtil.getValue("['" + id + "']", ExcelDef.class);
		
		if(def == null)
			throw new FailureException(ErrorCodeConstant.E0001S004, new Object[]{id});
		
		List<Column> columns = def.getColumns();
		int x = 0;
		for(Column column : columns) {
			sheet.addCell(new Label(x, 0, column.getHeadtext()));
			x++;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String id, WritableSheet sheet, Object item, int y) throws Exception {
		ExcelDef def = registry.getMeta(id);
		if(def == null)
			def = SwapAreaUtil.getValue("['" + id + "']", ExcelDef.class);
		
		if(def == null)
			throw new FailureException(ErrorCodeConstant.E0001S004, new Object[]{id});
		
		List<Column> columns = def.getColumns();
		try {
			int x = 0;
			Object obj;
			for(Column column : columns) {
				
				if(item instanceof Map)
					obj = ((Map<String, Object>)item).get(column.getDatafield());
				else
					obj = PropertyUtils.getProperty(item, column.getDatafield());
				
				if(obj == null) {
					sheet.addCell(new Label(x, y, ""));
				} else if("label".equals(column.getType()))
					sheet.addCell(new Label(x, y, (String)obj));
				else if("number".equals(column.getType()))
					sheet.addCell(new Number(x, y, (Double)obj));
				else if("datetime".equals(column.getType()))
					sheet.addCell(new DateTime(x, y, (Date)obj));
				else if("formula".equals(column.getType()))
					sheet.addCell(new Formula(x, y, (String)obj));
				else
					throw new RuntimeException("column type error!");
				x++;
			}
			
		} catch (Exception e) {
			logger.error(LogCodeConstant.SYS00009, e);
			throw e;
		}
	}
	
}
