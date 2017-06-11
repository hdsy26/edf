package org.edf.hifox.security.adapter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.edf.hifox.core.chain.Chain;
import org.edf.hifox.core.chain.selector.ChainSelector;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.util.SwapAreaUtil;
import org.edf.hifox.security.constant.SecurityConstant;

public class DefaultCipherAdapter implements CipherAdapter {
	private ChainSelector enChainSelector;
	private ChainSelector deChainSelector;

	@Override
	public String encrypt(String input) {
		try {
			// 适配加密
			Body body = SwapAreaUtil.getInboundRequestMessage().getBody();
			
			Chain enChain = enChainSelector.select(body);
	
			SwapAreaUtil.setObjectUniqueMark(enChain.objectUniqueMark());
	
			enChain.doChain(input);
			
			String result = SwapAreaUtil.getEncryptedText();
			
			// 追加加密链信息
			String objectUniqueMark = enChain.objectUniqueMark();
			byte[] encodedObjectUniqueMark = Base64.encodeBase64(objectUniqueMark.getBytes(SecurityConstant.OBJECT_UNIQUE_MARK_CHARSET_NAME));
			String encodedStringObjectUniqueMark = new String(encodedObjectUniqueMark, SecurityConstant.OBJECT_UNIQUE_MARK_CHARSET_NAME);
			
			result = StringUtils.join(result, SecurityConstant.OBJECT_PREFIX, encodedStringObjectUniqueMark);
	
			return result;
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S060, new Object[]{e.getMessage()}, e);
		}
	}

	@Override
	public String decrypt(String input) {
		try {
			// 获取加密链信息
			String encodedStringObjectUniqueMark = StringUtils.substringAfter(input, SecurityConstant.OBJECT_PREFIX);
			byte[] decodedObjectUniqueMark = Base64.decodeBase64(encodedStringObjectUniqueMark.getBytes(SecurityConstant.OBJECT_UNIQUE_MARK_CHARSET_NAME));
			String objectUniqueMark = new String(decodedObjectUniqueMark, SecurityConstant.OBJECT_UNIQUE_MARK_CHARSET_NAME);
			
			// 适配解密
			Chain deChain = deChainSelector.select(objectUniqueMark);
	
			deChain.doChain(input);
	
			String result = SwapAreaUtil.getDecryptedText();
	
			return result;
		} catch (Exception e) {
			throw new FailureException(ErrorCodeConstant.E0001S061, new Object[]{e.getMessage()}, e);
		}
	}
	

	public void setEnChainSelector(ChainSelector enChainSelector) {
		this.enChainSelector = enChainSelector;
	}

	public void setDeChainSelector(ChainSelector deChainSelector) {
		this.deChainSelector = deChainSelector;
	}
	
}
