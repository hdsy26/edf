package org.edf.hifox.security.cipher.support;

import java.security.SecureRandom;

import org.edf.hifox.security.cipher.AbstractDecipher;

/**
 * 非对称
 * @author wangyang01
 *
 */
public class RsaDecipher extends AbstractDecipher {
	private String algorithm = "RSA";
	private String mode = "ECB";
	private String padding = "PKCS1Padding";
	private SecureRandom secureRandom = new SecureRandom(new byte[]{});
	
	@Override
	protected String obtainAlgorithm() {
		return algorithm;
	}
	@Override
	protected String obtainMode() {
		return mode;
	}
	@Override
	protected String obtainPadding() {
		return padding;
	}
	@Override
	protected SecureRandom obtainSecureRandom() {
		return secureRandom;
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
