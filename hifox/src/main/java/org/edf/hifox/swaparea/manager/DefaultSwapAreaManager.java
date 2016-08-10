package org.edf.hifox.swaparea.manager;

import org.edf.hifox.expression.ExpressionParser;
import org.edf.hifox.swaparea.DefaultSwapArea;
import org.edf.hifox.swaparea.SwapArea;
import org.edf.hifox.swaparea.SwapAreaHolder;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultSwapAreaManager implements SwapAreaManager {
	/**
	 * 数据交换区存储管理器变量
	 */
	private SwapAreaHolder swapAreaHolder;

	/**
	 * 表达式解析器
	 */
	private ExpressionParser expressionParser;

	public DefaultSwapAreaManager(SwapAreaHolder swapAreaHolder, ExpressionParser expressionParser) {
		this.swapAreaHolder = swapAreaHolder;
		this.expressionParser = expressionParser;
	}

	@Override
	public SwapArea buildNewSwapArea() {
		SwapArea swapArea = swapAreaHolder.getCurrentSwapArea();
		if(swapArea == null) {
			swapArea = new DefaultSwapArea(expressionParser);
			swapAreaHolder.setCurrentSwapArea(swapArea);
		}
		return swapArea;
	}

	@Override
	public SwapArea getCurrentSwapArea() {
		return swapAreaHolder.getCurrentSwapArea();
	}

	@Override
	public SwapArea releaseCurrentSwapArea() {
		return swapAreaHolder.removeCurrentSwapArea();
	}

}
