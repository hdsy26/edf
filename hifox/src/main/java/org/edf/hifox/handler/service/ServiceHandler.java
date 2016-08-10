package org.edf.hifox.handler.service;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.edf.hifox.chain.invocation.Invocation;
import org.edf.hifox.constant.FormatConstant;
import org.edf.hifox.constant.LogCodeConstant;
import org.edf.hifox.constant.RespStatusConstant;
import org.edf.hifox.datatransfer.Body;
import org.edf.hifox.datatransfer.Message;
import org.edf.hifox.datatransfer.support.RequestHead;
import org.edf.hifox.datatransfer.support.ResponseHead;
import org.edf.hifox.datatransfer.support.ResponseMessage;
import org.edf.hifox.handler.Handler;
import org.edf.hifox.invoker.Invoker;
import org.edf.hifox.reqinfo.InboundRequestInfo;
import org.edf.hifox.util.MessageUtil;
import org.edf.hifox.util.SpringContextUtil;
import org.edf.hifox.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class ServiceHandler implements Handler<InboundRequestInfo> {
	
	private Invoker<Body, InboundRequestInfo> invoker;
	
	public void setInvoker(Invoker<Body, InboundRequestInfo> invoker) {
		this.invoker = invoker;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handle(InboundRequestInfo data, Invocation invocation) {
		Body body = invoker.invoke(data);

		RequestHead reqHead = MessageUtil.getRequestHead();

		ResponseMessage respMsg = new ResponseMessage();
		ResponseHead respHead = new ResponseHead();
		respHead.setSysRespNodeId(MessageUtil.getNodeId());
		respHead.setSysTargetNodeId(reqHead.getSysReqNodeId());
		respHead.setSysEventTraceId(reqHead.getSysEventTraceId());
		respHead.setSysRecvDatetime(DateFormatUtils.format(data.getReceiveTime(), FormatConstant.YMDHMSS));
		respHead.setSysRespDatetime(DateFormatUtils.format(new Date(), FormatConstant.YMDHMSS));
		respHead.setSysRespStatus(RespStatusConstant.SUCCESS);
		String respDesc = SpringContextUtil.getMessageNonstrict(LogCodeConstant.SYS00000, reqHead.getSysLanguage(), reqHead.getSysCountry());
		respHead.setSysRespDesc(respDesc);
		respMsg.setHead(respHead);
		respMsg.setBody(body);
		
		SwapAreaUtil.setInboundResponseMessage((Message)respMsg);
		
		invocation.invoke(data);
		
	}

}
