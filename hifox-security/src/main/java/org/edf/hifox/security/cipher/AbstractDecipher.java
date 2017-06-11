package org.edf.hifox.security.cipher;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.security.meta.CipherMetainfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public abstract class AbstractDecipher implements Decipher, InitializingBean {
	private KeyStore keyStore;
	private Key key;
	
	private Resource resource;
	private String charsetName = "UTF-8";
	private String alias;
	private String storeType;
	private String storePassword;
	private String keyPassword;
	
	private CipherMetainfo metainfo;
	
	protected abstract String obtainAlgorithm();
	protected abstract String obtainMode();
	protected abstract String obtainPadding();
	protected abstract SecureRandom obtainSecureRandom();
	
	@Override
	public byte[] decrypt(byte[] input) {
		try {
			// 解码
			byte[] decoded = Base64.decodeBase64(input);
			
			// 解密
			Cipher decipher = Cipher.getInstance(obtainAlgorithm() + "/" + obtainMode() + "/" + obtainPadding());
			decipher.init(Cipher.DECRYPT_MODE, key, obtainSecureRandom());
			byte[] decodedDecrypted = decipher.doFinal(decoded);
			
			return decodedDecrypted;
			
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S061, new Object[]{e.getMessage()}, e);
		}
		
	}

	@Override
	public String decrypt(String input) {
		try {
			byte[] decodedDecrypted = decrypt(input.getBytes(charsetName));
			
			String decodedDecryptedString = new String(decodedDecrypted, charsetName);
			
			return decodedDecryptedString;
			
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
		InputStream is = null;
		try {
			keyStore = KeyStore.getInstance(storeType);
			is = resource.getInputStream();
			keyStore.load(is, storePassword.toCharArray());
			key = keyStore.getKey(alias, keyPassword.toCharArray());
		} finally {
			if (is != null)
				is.close();
		}
		
		metainfo = new CipherMetainfo();
		metainfo.setKeyStorePath(resource.getFile().getAbsolutePath());
		metainfo.setStoreType(storeType);
		metainfo.setCharsetName(charsetName);
		metainfo.setAlias(alias);
		metainfo.setAlgorithm(obtainAlgorithm());
		metainfo.setMode(obtainMode());
		metainfo.setPadding(obtainPadding());
		
		String info = StringUtils.join(metainfo.getKeyStorePath(), 
				"|", metainfo.getStoreType(),
				"|", metainfo.getCharsetName(),
				"|", metainfo.getAlias(),
				"|", metainfo.getAlgorithm(),
				"|", metainfo.getMode(),
				"|", metainfo.getPadding());
		
		metainfo.setInfo(info.getBytes(charsetName));
	}
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public void setStorePassword(String storePassword) {
		this.storePassword = storePassword;
	}
	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}

}
