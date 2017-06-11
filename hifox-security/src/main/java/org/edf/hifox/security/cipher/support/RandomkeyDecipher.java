package org.edf.hifox.security.cipher.support;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.util.SwapAreaUtil;
import org.edf.hifox.security.cipher.Decipher;
import org.edf.hifox.security.meta.CipherMetainfo;
import org.springframework.beans.factory.InitializingBean;

public class RandomkeyDecipher implements Decipher, InitializingBean {
	private Decipher baseDecipher;
	
	private String charsetName = "UTF-8";
	
	private String algorithm = "DES";
	private String mode = "ECB";
	private String padding = "PKCS5Padding";
	private SecureRandom secureRandom = new SecureRandom(new byte[]{});
	
	private CipherMetainfo metainfo;

	@Override
	public byte[] decrypt(byte[] input) {
		try {
			byte[] encryptedRandomkey = SwapAreaUtil.getEncryptedRandomkey();
			
			byte[] decryptedRandomkey = baseDecipher.decrypt(encryptedRandomkey);
			
			SecretKey key = new SecretKeySpec(decryptedRandomkey, algorithm);
			
			byte[] decodedEncrypted = Base64.decodeBase64(input);
			
			Cipher decipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
			decipher.init(Cipher.DECRYPT_MODE, key, secureRandom);
			
			byte[] decodedDecrypted = decipher.doFinal(decodedEncrypted);
			
			return decodedDecrypted;
			
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S061, new Object[]{e.getMessage()}, e);
		}
	}

	@Override
	public String decrypt(String input) {
		try {
			byte[] decryptedEncoded = decrypt(input.getBytes(charsetName));
			
			String decryptedEncodedString = new String(decryptedEncoded, charsetName);
			
			return decryptedEncodedString;
			
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S061, new Object[]{e.getMessage()}, e);
		}
	}

	@Override
	public CipherMetainfo metainfo() {
		return metainfo;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		metainfo = new CipherMetainfo();
		metainfo.setCharsetName(charsetName);
		metainfo.setAlgorithm(algorithm);
		metainfo.setMode(mode);
		metainfo.setPadding(padding);
		
		String info = StringUtils.join(metainfo.getCharsetName(),
				"|", metainfo.getAlgorithm(),
				"|", metainfo.getMode(),
				"|", metainfo.getPadding());
		
		metainfo.setInfo(info.getBytes(charsetName));
	}
	

	public void setBaseDecipher(Decipher baseDecipher) {
		this.baseDecipher = baseDecipher;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setPadding(String padding) {
		this.padding = padding;
	}

	public void setSecureRandom(SecureRandom secureRandom) {
		this.secureRandom = secureRandom;
	}
	

}
