package org.edf.hifox.swaparea.manager;

import org.edf.hifox.swaparea.SwapArea;

/**
 * 
 * @author WangYang
 *
 */
public interface SwapAreaManager {

	/**
	 * 为当前请求构建并初始化一个新的数据交换区实例。
	 * 
	 * @return 创建的数据交换区实例
	 */
	SwapArea buildNewSwapArea();

	/**
	 * 获取当前请求的数据交换区实例。
	 * 
	 * @return 当前请求绑定的数据交换区实例
	 */
	SwapArea getCurrentSwapArea();

	/**
	 * 释放当前请求的数据交换区实例。
	 * 
	 * @return 当前请求绑定的数据交换区实例
	 */
	SwapArea releaseCurrentSwapArea();

}
