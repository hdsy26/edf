package org.edf.hifox.security.support;

import javax.annotation.Resource;

import org.edf.hifox.security.cipher.Decipher;
import org.edf.hifox.security.cipher.Encipher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:framework/spring/*-applicationContext.xml"})
public class RSACipherTest {
	
	@Resource(name = "rsaEncipher")
	private Encipher encipher;
	
	@Resource(name = "rsaDecipher")
	private Decipher decipher;

	@Test
	public void testEncryptDecrypt() throws Exception {
		long time = System.currentTimeMillis();
		
		String str = encipher.encrypt("abcd你好abcd你好");
		System.out.println("encrypt:" + str);
		
		str = decipher.decrypt(str);
		System.out.println("decrypt:" + str);
		
		System.out.println("time:" + (System.currentTimeMillis() - time));
	}

}
