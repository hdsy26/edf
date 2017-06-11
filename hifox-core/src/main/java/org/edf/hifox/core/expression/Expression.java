package org.edf.hifox.core.expression;

/**
 * 
 * @author WangYang
 *
 */
public interface Expression {

	/**
	 * 从表达式对应的上下文中取值
	 * 
	 * @param root 存储表达式对应值的上下文
	 * @return 表达式对应的值
	 */
	Object getValue(Object root);

	/**
	 * 从表达式对应的上下文中按照指定的数据类型取值
	 * 
	 * @param root 存储表达式对应值的上下文
	 * @param clazz 目标数据类型对应的类
	 * @return 表达式对应的值
	 */
	<T> T getValue(Object root, Class<T> clazz);

	/**
	 * 将值设置到表达式对应的上下文中
	 * 
	 * @param value 表达式对应的值
	 * @param root 存储表达式对应值的上下文
	 */
	void setValue(Object value, Object root);
}
