package org.edf.hifox.security.cipher;

import org.edf.hifox.security.meta.CipherMetainfo;

/**
 * 解密接口
 * @author wangyang01
 *
 */
public interface Decipher {
	byte[] decrypt(byte[] input);
	String decrypt(String input);
	CipherMetainfo metainfo();
}
