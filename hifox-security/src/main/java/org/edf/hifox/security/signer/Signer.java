package org.edf.hifox.security.signer;


public interface Signer {
	void update(byte[] input);
	void update(String input, String charsetName);
	byte[] sign();
	String sign(String charsetName);
}
