package org.edf.hifox.core.register;

import org.edf.hifox.core.util.SpringContextUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

/**
 * 
 * @author WangYang
 *
 */
public abstract class AbstractRegister implements Register, InitializingBean {
	
	protected Resource[] resources;
	private SpringContextUtil springContextUtil;
	
	public void setResources(Resource[] resources) {
		this.resources = resources;
	}

	public void setSpringContextUtil(SpringContextUtil springContextUtil) {
		this.springContextUtil = springContextUtil;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		regist(resources);
	}
	
	@SuppressWarnings("static-access")
	protected final <E> E getBean(String beanId, Class<E> clazz) {
		return springContextUtil.getBean(beanId, clazz);
	}

}
