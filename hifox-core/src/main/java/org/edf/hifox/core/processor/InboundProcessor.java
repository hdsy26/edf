package org.edf.hifox.core.processor;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.edf.hifox.core.chain.Chain;
import org.edf.hifox.core.chain.selector.ChainSelector;
import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;
import org.edf.hifox.core.reqinfo.InboundRequestInfo;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class InboundProcessor implements Processor<byte[], InboundRequestInfo> {

	private static final Logger logger = LoggerFactory.getLogger(InboundProcessor.class);

	private ChainSelector selector;
	private String requestEncoding;
	private String responseEncoding;
	private String serviceIdXmlPath;

	public void setSelector(ChainSelector selector) {
		this.selector = selector;
	}

	public void setRequestEncoding(String requestEncoding) {
		this.requestEncoding = requestEncoding;
	}

	public void setResponseEncoding(String responseEncoding) {
		this.responseEncoding = responseEncoding;
	}

	public void setServiceIdXmlPath(String serviceIdXmlPath) {
		this.serviceIdXmlPath = serviceIdXmlPath;
	}

	@Override
	public byte[] process(InboundRequestInfo data) {

		byte[] bytes = new byte[0];
		try {
			String contentString = new String(data.getContent(), requestEncoding);

			logger.info(LogCodeConstant.SYS00002, new Object[] {contentString});

			data.setContentString(contentString);

			Document document = DocumentHelper.parseText(data.getContentString());
			Node node = document.selectSingleNode(serviceIdXmlPath);
			data.setServiceId(node.getText());

			Chain chain = selector.select(data);
			chain.doChain(data);

			String respMsgStr = SwapAreaUtil.getInboundResponseMsgStr();
			
			logger.info(LogCodeConstant.SYS00003, new Object[]{respMsgStr});

			bytes = respMsgStr.getBytes(responseEncoding);

		} catch (Throwable t) {
			logger.error(LogCodeConstant.SYS00009, t);
		}
		return bytes;
	}

}
