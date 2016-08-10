package org.edf.hifox.register.service;

import org.edf.hifox.constant.LogCodeConstant;
import org.edf.hifox.log.Logger;
import org.edf.hifox.log.LoggerFactory;
import org.edf.hifox.parser.Parser;
import org.edf.hifox.register.AbstractRegister;
import org.edf.hifox.register.service.registry.ServiceRegistry;
import org.edf.hifox.register.service.registry.xmlbean.ServiceCfg;
import org.edf.hifox.register.service.registry.xmlbean.ServiceDef;
import org.springframework.core.io.Resource;

/**
 * 
 * @author WangYang
 *
 */
public class ServiceConfigRegister extends AbstractRegister {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceConfigRegister.class);
	
	private Parser<ServiceCfg> parser;
	private ServiceRegistry registry;
	
	public void setParser(Parser<ServiceCfg> parser) {
		this.parser = parser;
	}

	public void setRegistry(ServiceRegistry registry) {
		this.registry = registry;
	}


	@Override
	public void regist(Resource[] resources) throws Exception {
		logger.info(LogCodeConstant.REG00003);
		ServiceCfg serviceCfg;
		for(Resource resource : resources) {
			logger.info(LogCodeConstant.REG00004, new Object[]{resource.getURI()});
			serviceCfg = parser.parse(resource);
			for(ServiceDef serviceDef : serviceCfg.getServiceDefs()) {
				registry.addUniqueMeta(serviceDef.getId(), serviceDef);
			}
		}
		
	}
	
}
