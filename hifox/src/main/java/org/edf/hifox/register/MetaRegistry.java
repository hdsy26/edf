package org.edf.hifox.register;

import java.util.HashMap;
import java.util.Map;

import org.edf.hifox.exception.InitializationException;

/**
 * 
 * @author WangYang
 *
 */
public class MetaRegistry<E> {

	protected Map<String, E> metas = new HashMap<String, E>();

	public void addMeta(String key, E meta) {
		metas.put(key, meta);
	}
	
	public void addUniqueMeta(String key, E meta) {
		if(metas.containsKey(key)) {
			throw new InitializationException(key + " already exist!");
		}
		metas.put(key, meta);
	}

	public Map<String, E> getMetas() {
		return metas;
	}

	public E getMeta(String key) {
		return metas.get(key);
	}
	
	public final boolean exists(String key) {
		return metas.containsKey(key);
	}

}