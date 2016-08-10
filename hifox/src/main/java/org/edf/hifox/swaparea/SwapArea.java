package org.edf.hifox.swaparea;

import java.util.Map;

/**
 * 
 * @author WangYang
 *
 */
public interface SwapArea extends Map<String, Object> {
	
	/**
	 * 获取内部数据交换区中指定路径中的某个域的值
	 * 
	 * @param path 路径表达式
	 * @return 返回内部数据交换区中指定路径的域的值
	 */
	Object getValue(String path);

	/**
	 * 获取内部数据交换区中指定路径中的某个域的指定类型值
	 * 
	 * @param path 路径表达式
	 * @param clazz
	 * @return 返回内部数据交换区中指定路径的域的值
	 */
	<T> T getValue(String path, Class<T> clazz);

	/**
	 * 根据指定的路径表达式，设置内部数据交换区中对应域的值
	 * @param path 路径表达式
	 * @param value 值
	 */
	void setValue(String path, Object value);

	/**
	 * 指定区域内存在目标节点是否存在
	 * 
	 * @param path 路径表达式
	 * @return true目标节点存在，false目标节点不存在
	 */
	boolean containsPath(String path);
}
