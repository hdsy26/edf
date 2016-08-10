package org.edf.hifox.swaparea;

/**
 * 
 * @author WangYang
 *
 */
public interface SwapAreaHolder {

	/**
	 * 将数据交换区实例绑定到当前线程上下文
	 * 
	 * @param swapArea 数据交换区实例
	 */
	void setCurrentSwapArea(SwapArea swapArea);

	/**
	 * 获取与当前线程上下文绑定的数据交换区实例
	 * 
	 * @return 数据交换区实例
	 */
	SwapArea getCurrentSwapArea();

	/**
	 * 释放与当前线程上下文绑定的数据交换区实例
	 * @return 数据交换区实例
	 */
	SwapArea removeCurrentSwapArea();

}
