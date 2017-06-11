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

public abstract class AbstractEncipher implements Encipher, InitializingBean {
	private KeyStore keyStore;
	private Key key;
	
	private Resource resource;
	private String charsetName = "UTF-8";
	private String alias;
	private String storeType;
	private String storePassword;
	private String keyPassword;
	
	private CipherMetainfo metainfo;
	
	protected abstract boolean isSymmetric();
	protected abstract String obtainAlgorithm();
	protected abstract String obtainMode();
	protected abstract String obtainPadding();
	protected abstract SecureRandom obtainSecureRandom();
	
	@Override
	public byte[] encrypt(byte[] input) {
		try {
			// 加密
			Cipher encipher = Cipher.getInstance(obtainAlgorithm() + "/" + obtainMode() + "/" + obtainPadding());
			encipher.init(Cipher.ENCRYPT_MODE, key, obtainSecureRandom());
			byte[] encrypted = encipher.doFinal(input);
			
			// 加码
			byte[] encryptedEncoded = Base64.encodeBase64(encrypted);
			
			return encryptedEncoded;
			
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S060, new Object[]{e.getMessage()}, e);
		}
	}

	@Override
	public String encrypt(String input) {
		try {
			byte[] encryptedEncoded = encrypt(input.getBytes(charsetName));
			
			String encryptedEncodedString = new String(encryptedEncoded, charsetName);
			
			return encryptedEncodedString;
			
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S060, new Object[]{e.getMessage()}, e);
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
			if (isSymmetric())
				key = keyStore.getKey(alias, keyPassword.toCharArray());
			else
				key = keyStore.getCertificate(alias).getPublicKey();
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
