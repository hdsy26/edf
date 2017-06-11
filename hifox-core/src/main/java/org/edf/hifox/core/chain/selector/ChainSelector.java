package org.edf.hifox.core.chain.selector;

import org.edf.hifox.core.chain.Chain;

/**
 * 
 * @author WangYang
 *
 */
public interface ChainSelector {
	
	/**
	 * 根据不同请求接入的上下文环境，获取对应的处理链
	 * 
	 * @param context 交易上下文
	 * @return 处理链
	 */
	Chain select(Object context);

}
