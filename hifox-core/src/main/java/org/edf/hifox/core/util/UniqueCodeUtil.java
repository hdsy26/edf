package org.edf.hifox.core.util;

import java.util.UUID;

/**
 * 
 * @author WangYang
 *
 */
public final class UniqueCodeUtil {
	
	private UniqueCodeUtil() {
		
	}
	
	public static String randomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
