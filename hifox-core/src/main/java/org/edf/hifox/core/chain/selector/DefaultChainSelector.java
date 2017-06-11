package org.edf.hifox.core.chain.selector;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.edf.hifox.core.chain.Chain;
import org.edf.hifox.core.expression.ExpressionParser;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultChainSelector implements ChainSelector, InitializingBean {

	/**
	 * 处理链Map
	 */
	private Map<String, Chain> chainMap = new HashMap<String, Chain>();
	/**
	 * 表达式解析器
	 */
	private ExpressionParser expressionParser;
	private Chain defaultChain;
	

	public void setExpressionParser(ExpressionParser expressionParser) {
		this.expressionParser = expressionParser;
	}
	public void setDefaultChain(Chain defaultChain) {
		this.defaultChain = defaultChain;
	}

	public DefaultChainSelector(Map<String, Chain> chainMap) {
		this.chainMap.putAll(chainMap);
	}

	@Override
	public Chain select(Object context) {
		for (Entry<String, Chain> stackEntry : chainMap.entrySet()) {
			boolean result = this.expressionParser.getValue(stackEntry.getKey(), context, boolean.class);
			if (result) {
				return stackEntry.getValue();
			}
		}
		return this.defaultChain;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.defaultChain == null) {
			throw new NullPointerException("defaultChain is null!");
		}

		if (this.expressionParser == null) {
			throw new NullPointerException("defaultChain is null!");
		}
	}

}
