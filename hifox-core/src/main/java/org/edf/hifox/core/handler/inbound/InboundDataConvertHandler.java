package org.edf.hifox.core.handler.inbound;

import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.constant.ConvertConstant;
import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.datatransfer.Head;
import org.edf.hifox.core.datatransfer.Message;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.reqinfo.InboundRequestInfo;
import org.edf.hifox.core.util.DataConvertUtil;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class InboundDataConvertHandler implements Handler<InboundRequestInfo> {
	
	@Override
	public void handle(InboundRequestInfo data, Invocation invocation) {
		Message<Head, Body> requestMessage = DataConvertUtil.convert(data.getServiceId() + ConvertConstant.REQ, data.getContentString());
		data.setRequestMessage(requestMessage);
		
		invocation.invoke(data);
		
		Message<Head, Body> respMsg = SwapAreaUtil.getInboundResponseMessage();
		String respMsgStr = DataConvertUtil.convert(data.getServiceId() + ConvertConstant.RESP, respMsg);
		SwapAreaUtil.setInboundResponseMsgStr(respMsgStr);
	}

}
