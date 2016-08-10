package org.edf.hifox.expression.spel;

import org.edf.hifox.expression.Expression;

/**
 * 
 * @author WangYang
 *
 */
public class SpelExpression implements Expression {

	private org.springframework.expression.Expression spelExpression;

	public SpelExpression(org.springframework.expression.Expression expression) {
		this.spelExpression = expression;
	}

	@Override
	public Object getValue(Object root) {
		return this.spelExpression.getValue(root);
	}

	@Override
	public <T> T getValue(Object root, Class<T> clazz) {
		return this.spelExpression.getValue(root, clazz);
	}

	@Override
	public void setValue(Object value, Object root) {
		this.spelExpression.setValue(root, value);
	}

}
