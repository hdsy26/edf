package org.edf.hifox.core.register.service.registry.xmlbean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author WangYang
 *
 */
public class ServiceDef {
	private String id;
	private String beanId;
	private String methodName;
	
	private List<Argument> arguments = new ArrayList<Argument>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBeanId() {
		return beanId;
	}
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public List<Argument> getArguments() {
		return arguments;
	}
	public void setArguments(List<Argument> arguments) {
		this.arguments = arguments;
	}
	
}
