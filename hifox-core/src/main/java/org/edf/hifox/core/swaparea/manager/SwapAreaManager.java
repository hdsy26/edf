package org.edf.hifox.core.swaparea.manager;

import org.edf.hifox.core.swaparea.SwapArea;

/**
 * 
 * @author WangYang
 *
 */
public interface SwapAreaManager {

	SwapArea buildNewSwapArea();

	SwapArea getCurrentSwapArea();

	SwapArea releaseCurrentSwapArea();

}
