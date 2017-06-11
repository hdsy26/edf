package org.edf.hifox.security.handler;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.util.SwapAreaUtil;
import org.edf.hifox.security.cipher.Decipher;
import org.edf.hifox.security.constant.SecurityConstant;
import org.edf.hifox.security.meta.Metainfo;

public class DecipherHandler implements Handler<String> {
	
	private Decipher decipher;
	
	@Override
	public void handle(String data, Invocation invocation) {
		Metainfo metainfo = decipher.metainfo();
		try {
			String targetText = StringUtils.substringBefore(data, SecurityConstant.METAINFO_PREFIX);
			String encryptedEncodedStringRandomkey = StringUtils.substringBetween(data, SecurityConstant.RANDOMKEY_PREFIX, SecurityConstant.OBJECT_PREFIX);
			
			if (encryptedEncodedStringRandomkey != null) {
				byte[] encryptedEncodedRandomkey = encryptedEncodedStringRandomkey.getBytes(metainfo.getCharsetName());
				byte[] encryptedRandomkey = Base64.decodeBase64(encryptedEncodedRandomkey);
				SwapAreaUtil.setEncryptedRandomkey(encryptedRandomkey);
			}
			
			String result = decipher.decrypt(targetText);
			
			if (encryptedEncodedStringRandomkey != null) {
				SwapAreaUtil.setEncryptedRandomkey(null);
			}
			
			if (!invocation.hasNext()) {
				SwapAreaUtil.setDecryptedText(result);
			} else {
				invocation.invoke(result);
			}
			
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S061, new Object[]{e.getMessage()}, e);
		}
	}

	public void setDecipher(Decipher decipher) {
		this.decipher = decipher;
	}
	
}
