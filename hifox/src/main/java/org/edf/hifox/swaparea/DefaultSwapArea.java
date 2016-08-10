package org.edf.hifox.swaparea;

import java.util.HashMap;

import org.edf.hifox.expression.ExpressionParser;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultSwapArea extends HashMap<String, Object> implements SwapArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1948376248793162398L;

	private ExpressionParser expressionParser;

	public DefaultSwapArea(ExpressionParser expressionParser) {
		this.expressionParser = expressionParser;
	}

	@Override
	public Object getValue(String path) {
		return expressionParser.getValue(path, this);
	}

	@Override
	public <T> T getValue(String path, Class<T> clazz) {
		return expressionParser.getValue(path, this, clazz);
	}

	@Override
	public void setValue(String path, Object value) {
		expressionParser.setValue(path, value, this);
	}

	@Override
	public boolean containsPath(String path) {
		try {
			Object object = expressionParser.getValue(path, this);
			if (object == null)
				return false;
		} catch (Throwable t) {
			return false;
		}
		return true;
	}

}
