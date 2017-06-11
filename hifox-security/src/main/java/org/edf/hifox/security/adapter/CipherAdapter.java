package org.edf.hifox.security.adapter;

public interface CipherAdapter {
	String encrypt(String input);
	String decrypt(String input);
}
