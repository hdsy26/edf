package org.edf.hifox.security.signer.support;

import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.util.SwapAreaUtil;
import org.edf.hifox.security.signer.Signer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class DefaultSigner implements Signer, InitializingBean {
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private String algorithm;
	private KeyStore keyStore;
	private Key key;
	
	private Resource resource;
	private String charsetName;
	private String alias;
	private String storeType;
	private String storePassword;
	private String keyPassword;
	private String mode = "ECB";
	private String padding = "PKCS5Padding";
	private SecureRandom secureRandom = new SecureRandom(new byte[]{});
	
	private String swapAreaPath;

	@Override
	public void update(byte[] input) {
		try {
			Signature signature = SwapAreaUtil.getValue(swapAreaPath, Signature.class);
			if (signature == null) {
				signature = Signature.getInstance(algorithm);
				signature.initSign((PrivateKey)key, secureRandom);
				SwapAreaUtil.setValue(swapAreaPath, signature);
			}
			
			signature.update(input);
			
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S070, new Object[]{algorithm}, e);
		}
	}
	
	
	@Override
	public void update(String input, String charsetName) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public byte[] sign() {
		try {
			Signature signature = SwapAreaUtil.getValue(swapAreaPath, Signature.class);
			return signature.sign();
		} catch (SignatureException e) {
			throw new FailureException(ErrorCodeConstant.E0001S070, new Object[]{algorithm}, e);
		}
		
	}
	
	@Override
	public String sign(String charsetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		InputStream is = null;
		try {
			keyStore = KeyStore.getInstance(storeType);
			is = resource.getInputStream();
			keyStore.load(is, storePassword.toCharArray());
		} finally {
			if (is != null)
				is.close();
		}
		
		key = keyStore.getKey(alias, keyPassword.toCharArray());
		
		StringBuilder temp = new StringBuilder();
		temp.append("['_").append(resource.getFile().getAbsolutePath()).append("_").append(alias).append("_SIGNER']");
		
		swapAreaPath = temp.toString();
	}
	
	
	
	public static void main(String[] args) throws Exception {
		for(byte b : "\n".getBytes("UTF-8"))
			System.out.println(b);
	}


	


	

}
