package org.edf.hifox.handler.inbound;

import org.edf.hifox.chain.invocation.Invocation;
import org.edf.hifox.constant.ConvertConstant;
import org.edf.hifox.datatransfer.Body;
import org.edf.hifox.datatransfer.Head;
import org.edf.hifox.datatransfer.Message;
import org.edf.hifox.handler.Handler;
import org.edf.hifox.reqinfo.InboundRequestInfo;
import org.edf.hifox.util.DataConvertUtil;
import org.edf.hifox.util.SwapAreaUtil;

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
