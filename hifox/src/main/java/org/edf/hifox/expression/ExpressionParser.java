package org.edf.hifox.expression;

/**
 * 
 * @author WangYang
 *
 */
public interface ExpressionParser {

	/**
	 * 
	 * @param el 表达式
	 * @return 表达式对象
	 */
	Expression parseExpression(String el);

	/**
	 * 
	 * @param el 表达式
	 * @param root 存储表达式对应值的上下文
	 * @return 表达式对应的值
	 */
	Object getValue(String el, Object root);

	/**
	 * 根据指定的表达式和目标数据类型从上下文中取值
	 * 
	 * @param el 表达式
	 * @param root 存储表达式对应值的上下文
	 * @param clazz 目标数据类型对应的类
	 * @return 表达式对应的值
	 */
	<T> T getValue(String el, Object root, Class<T> clazz);

	/**
	 * 根据指定的表达式将值设置到上下文中
	 * 
	 * @param el 表达式
	 * @param value 表达式对应的值
	 * @param root 存储表达式对应值的上下文
	 */
	void setValue(String el, Object value, Object root);

}
