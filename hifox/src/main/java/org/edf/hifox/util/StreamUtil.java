package org.edf.hifox.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.util.ByteArrayBuffer;

/**
 * 
 * @author WangYang
 *
 */
public class StreamUtil {

	public static byte[] readStream(InputStream is, boolean closeStream) throws IOException {
		if (is == null)
			throw new NullPointerException("inputStream is null");
		ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(0);
		try {
			byte[] tempBuffer = new byte[512];
			int length = -1;
			while ((length = is.read(tempBuffer)) != -1) {
				byteArrayBuffer.append(tempBuffer, 0, length);
			}
		} finally {
			if (closeStream) {
				is.close();
			}
		}
		return byteArrayBuffer.toByteArray();
	}

	public static String readStream(InputStream is, String encoding, boolean closeStream) throws IOException {
		byte[] bytes = readStream(is, closeStream);
		return new String(bytes, encoding);
	}

	public static void writeStream(OutputStream os, byte[] bytes, boolean closeStream) throws IOException {
		if (os == null)
			throw new NullPointerException("outputStream is null");
		try {
			os.write(bytes);
			os.flush();
		} finally {
			if (closeStream)
				os.close();
		}
	}
	
	public static void writeStream(OutputStream os, String str, String encoding, boolean closeStream) throws IOException {
		byte[] bytes = str.getBytes(encoding);
		writeStream(os, bytes, closeStream);
	}

}
