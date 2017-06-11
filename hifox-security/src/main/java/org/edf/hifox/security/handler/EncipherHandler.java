package org.edf.hifox.security.handler;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.util.SwapAreaUtil;
import org.edf.hifox.security.cipher.Encipher;
import org.edf.hifox.security.constant.SecurityConstant;
import org.edf.hifox.security.meta.CipherMetainfo;

public class EncipherHandler implements Handler<String> {
	
	private Encipher encipher;
	
	@Override
	public void handle(String data, Invocation invocation) {
		try {
			String result = encipher.encrypt(data);
			
			// 追加加密元数据信息
			CipherMetainfo em = encipher.metainfo();
			byte[] info = em.getInfo();
			byte[] encodedInfo = Base64.encodeBase64(info);
			String encodedStringInfo = new String(encodedInfo, em.getCharsetName());
			
			result = StringUtils.join(result, SecurityConstant.METAINFO_PREFIX, encodedStringInfo);
			
			byte[] encryptedRandomkey = SwapAreaUtil.getEncryptedRandomkey();
			if (encryptedRandomkey != null) {
				byte[] encryptedEncodedRandomkey = Base64.encodeBase64(encryptedRandomkey);
				String encryptedEncodedStringRandomkey = new String(encryptedEncodedRandomkey, em.getCharsetName());
				result = StringUtils.join(result, SecurityConstant.RANDOMKEY_PREFIX, encryptedEncodedStringRandomkey);
				SwapAreaUtil.setEncryptedRandomkey(null);
			}
			
			if (!invocation.hasNext()) {
				SwapAreaUtil.setEncryptedText(result);
			} else {
				invocation.invoke(result);
			}
			
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S060, new Object[]{e.getMessage()}, e);
		}
	}

	public void setEncipher(Encipher encipher) {
		this.encipher = encipher;
	}
	
}
