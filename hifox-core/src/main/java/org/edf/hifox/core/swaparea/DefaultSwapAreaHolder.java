package org.edf.hifox.core.swaparea;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultSwapAreaHolder implements SwapAreaHolder {
	
	private ThreadLocal<SwapArea> holder = new ThreadLocal<SwapArea>();

	public SwapArea getCurrentSwapArea() {
		return holder.get();
	}

	public SwapArea removeCurrentSwapArea() {
		SwapArea swapArea = holder.get();
		holder.remove();
		return swapArea;
	}

	public void setCurrentSwapArea(SwapArea swapArea) {
		holder.set(swapArea);
	}

}
