package org.edf.hifox.core.swaparea;

import java.util.Map;

/**
 * 
 * @author WangYang
 *
 */
public interface SwapArea extends Map<String, Object> {
	
	Object getValue(String path);

	<T> T getValue(String path, Class<T> clazz);

	void setValue(String path, Object value);

	boolean containsPath(String path);
}
