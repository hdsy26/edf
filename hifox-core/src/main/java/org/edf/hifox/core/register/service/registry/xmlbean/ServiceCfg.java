package org.edf.hifox.core.register.service.registry.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author WangYang
 *
 */
public class ServiceCfg {
	private List<ServiceDef> serviceDefs = new ArrayList<ServiceDef>();

	public List<ServiceDef> getServiceDefs() {
		return serviceDefs;
	}

	public void addServiceDef(ServiceDef serviceDef) {
		this.serviceDefs.add(serviceDef);
	}
	
}
