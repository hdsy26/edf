package org.edf.hifox.core.expression.spel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.edf.hifox.core.expression.Expression;
import org.edf.hifox.core.expression.ExpressionParser;

/**
 * 
 * @author WangYang
 *
 */
public class SpelExpressionParser implements ExpressionParser {
	
	/**
	 * 表达式实例缓存
	 */
	private static final Map<String, Expression> EXPRESSION_CACHE = new ConcurrentHashMap<String, Expression>();

	/**
	 * 使用SPEL的表达式解析器
	 */
	private org.springframework.expression.ExpressionParser realExpressionParser;

	/**
	 * 表达式实施缓存开关
	 */
	private boolean allowCache = true;

	/**
	 * 无参构造函数，new一个Spring EL的parser
	 */
	public SpelExpressionParser() {
		this.realExpressionParser = new org.springframework.expression.spel.standard.SpelExpressionParser();
	}

	@Override
	public Object getValue(String el, Object root) {
		Expression expression = this.parseExpression(el);
		return expression.getValue(root);
	}

	@Override
	public <T> T getValue(String el, Object root, Class<T> clazz) {
		Expression expression = this.parseExpression(el);
		return expression.getValue(root, clazz);
	}

	@Override
	public void setValue(String el, Object value, Object root) {
		Expression expression = this.parseExpression(el);
		expression.setValue(value, root);
	}

	@Override
	public Expression parseExpression(String el) {
		Expression expression = null;

		/**
		 * 从缓存读取
		 */
		if (allowCache) {
			expression = EXPRESSION_CACHE.get(el);
			if (expression != null) {
				return expression;
			}
		}

		/**
		 * 如果无缓存，则编译新的表达式实施
		 */
		expression = new SpelExpression(this.realExpressionParser.parseExpression(el));

		/**
		 * 缓存该表达式实例
		 */
		if (allowCache) {
			EXPRESSION_CACHE.put(el, expression);
		}

		return expression;
	}

}
