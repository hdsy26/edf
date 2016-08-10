package org.edf.hifox.util;

import java.util.UUID;

/**
 * 
 * @author WangYang
 *
 */
public class UniqueCodeUtil {
	public static String randomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
