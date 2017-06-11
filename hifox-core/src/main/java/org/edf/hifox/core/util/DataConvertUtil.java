package org.edf.hifox.core.util;

import org.edf.hifox.core.converter.manager.ConverterManager;

/**
 * 
 * @author WangYang
 *
 */
public final class DataConvertUtil {
	private static ConverterManager<Object, Object> converterManager;
	
	private DataConvertUtil() {
		
	}

	public static void setConverterManager(ConverterManager<Object, Object> converterManager) {
		DataConvertUtil.converterManager = converterManager;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convert(String mappingId, Object source) {
		return (T)converterManager.convert(mappingId, source);
	}
	
	
}
