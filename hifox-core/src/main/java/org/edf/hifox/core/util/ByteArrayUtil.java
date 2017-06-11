package org.edf.hifox.core.util;

import org.apache.http.util.ByteArrayBuffer;

/**
 * 
 * @author WangYang
 *
 */
public final class ByteArrayUtil {
	
	private ByteArrayUtil() {
		
	}

	public static byte[] join(byte[]... byteArrays) {
		if (byteArrays == null)
			throw new NullPointerException("byte array is null");
		
		ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(0);
		
		for (byte[] byteArray : byteArrays) {
			byteArrayBuffer.append(byteArray, 0, byteArray.length);
		}
		
		return byteArrayBuffer.toByteArray();
	}

}
