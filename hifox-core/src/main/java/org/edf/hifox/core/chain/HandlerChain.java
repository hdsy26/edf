package org.edf.hifox.core.chain;

import java.util.List;

import org.edf.hifox.core.chain.invocation.ChainInvocation;
import org.edf.hifox.core.handler.Handler;
import org.springframework.beans.factory.BeanNameAware;

/**
 * 
 * @author WangYang
 *
 */
public class HandlerChain implements Chain, BeanNameAware {
	private List<Handler<Object>> handlerList;
	private String idorname;

	public void setHandlerList(List<Handler<Object>> handlerList) {
		this.handlerList = handlerList;
	}

	@Override
	public void doChain(Object data) {
		new ChainInvocation(handlerList.iterator()).invoke(data);
	}

	@Override
	public void setBeanName(String name) {
		this.idorname = name;
	}

	@Override
	public String objectUniqueMark() {
		return idorname;
	}
	
}
