package org.edf.hifox.security.cipher.support;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.util.SwapAreaUtil;
import org.edf.hifox.security.cipher.Encipher;
import org.edf.hifox.security.meta.CipherMetainfo;
import org.springframework.beans.factory.InitializingBean;

public class RandomkeyEncipher implements Encipher, InitializingBean {
	private KeyGenerator keyGenerator;
	
	private Encipher baseEncipher;
	
	private String charsetName = "UTF-8";
	private String algorithm = "DES";
	private String mode = "ECB";
	private String padding = "PKCS5Padding";
	private SecureRandom secureRandom = new SecureRandom(new byte[]{});
	
	private CipherMetainfo metainfo;
	

	@Override
	public byte[] encrypt(byte[] input) {
		try {
			SecretKey key = keyGenerator.generateKey();
			
			Cipher encipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
			encipher.init(Cipher.ENCRYPT_MODE, key, secureRandom);
			
			byte[] encrypted = encipher.doFinal(input);
			
			byte[] encryptedEncoded = Base64.encodeBase64(encrypted);
			
			byte[] encryptedRandomkey = baseEncipher.encrypt(key.getEncoded());
			SwapAreaUtil.setEncryptedRandomkey(encryptedRandomkey);
			
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
		keyGenerator = KeyGenerator.getInstance(algorithm);
		keyGenerator.init(new SecureRandom());
		
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
	
	public static void main(String[] args) throws Exception {
		
		KeyGenerator kg = KeyGenerator.getInstance("DES"); 
		kg.init(new SecureRandom());
		SecretKey key = kg.generateKey();
		
		Cipher cipher = Cipher.getInstance("DES"); 
        cipher.init(Cipher.ENCRYPT_MODE, key); 
        System.out.println(new String(Base64.encodeBase64(cipher.doFinal("abcdabcdabcdabcdabcdabcdabcdabcdabcd".getBytes()))));
        
        key = kg.generateKey();
		
		cipher = Cipher.getInstance("DES"); 
        cipher.init(Cipher.ENCRYPT_MODE, key); 
        System.out.println(new String(Base64.encodeBase64(cipher.doFinal("abcdabcdabcdabcdabcdabcdabcdabcdabcd".getBytes()))));

        kg = KeyGenerator.getInstance("AES"); 
		kg.init(new SecureRandom());
		key = kg.generateKey();
		
		cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key); 
        System.out.println(new String(Base64.encodeBase64(cipher.doFinal("abcdabcdabcdabcdabcdabcdabcdabcdabcd".getBytes()))));
        
        key = kg.generateKey();
		
		cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key); 
        System.out.println(new String(Base64.encodeBase64(cipher.doFinal("abcdabcdabcdabcdabcdabcdabcdabcdabcd".getBytes()))));

	}

	public void setBaseEncipher(Encipher baseEncipher) {
		this.baseEncipher = baseEncipher;
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
