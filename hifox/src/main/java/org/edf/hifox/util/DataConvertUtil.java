package org.edf.hifox.util;

import org.edf.hifox.converter.manager.ConverterManager;

/**
 * 
 * @author WangYang
 *
 */
public class DataConvertUtil {
	private static ConverterManager<Object, Object> converterManager;

	public static ConverterManager<Object, Object> getConverterManager() {
		return converterManager;
	}

	public void setConverterManager(ConverterManager<Object, Object> converterManager) {
		DataConvertUtil.converterManager = converterManager;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convert(String mappingId, Object source) {
		return (T)converterManager.convert(mappingId, source);
	}
	
	
}
