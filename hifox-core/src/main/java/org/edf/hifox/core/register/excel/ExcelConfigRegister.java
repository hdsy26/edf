package org.edf.hifox.core.register.excel;

import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;
import org.edf.hifox.core.parser.Parser;
import org.edf.hifox.core.register.AbstractRegister;
import org.edf.hifox.core.register.excel.registry.ExcelRegistry;
import org.edf.hifox.core.register.excel.registry.xmlbean.ExcelCfg;
import org.edf.hifox.core.register.excel.registry.xmlbean.ExcelDef;
import org.springframework.core.io.Resource;

/**
 * 
 * @author WangYang
 *
 */
public class ExcelConfigRegister extends AbstractRegister {
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelConfigRegister.class);
	
	private Parser<ExcelCfg> parser;
	private ExcelRegistry registry;
	
	public void setParser(Parser<ExcelCfg> parser) {
		this.parser = parser;
	}

	public void setRegistry(ExcelRegistry registry) {
		this.registry = registry;
	}


	@Override
	public void regist(Resource[] resources) throws Exception {
		logger.info(LogCodeConstant.REG00007);
		ExcelCfg excelCfg;
		for(Resource resource : resources) {
			logger.info(LogCodeConstant.REG00008, new Object[]{resource.getURI()});
			excelCfg = parser.parse(resource);
			for(ExcelDef excelDef : excelCfg.getExcelDefs()) {
				registry.addUniqueMeta(excelDef.getId(), excelDef);
			}
		}
		
	}
	
}
