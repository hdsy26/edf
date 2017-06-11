package org.edf.hifox.core.swaparea.manager;

import org.edf.hifox.core.expression.ExpressionParser;
import org.edf.hifox.core.swaparea.DefaultSwapArea;
import org.edf.hifox.core.swaparea.SwapArea;
import org.edf.hifox.core.swaparea.SwapAreaHolder;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultSwapAreaManager implements SwapAreaManager {

	private SwapAreaHolder swapAreaHolder;

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
