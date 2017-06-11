package org.edf.hifox.core.handler.inbound;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.edf.hifox.core.chain.invocation.Invocation;
import org.edf.hifox.core.constant.ConvertConstant;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.constant.FormatConstant;
import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.constant.RespStatusConstant;
import org.edf.hifox.core.datatransfer.Message;
import org.edf.hifox.core.datatransfer.support.RequestHead;
import org.edf.hifox.core.datatransfer.support.ResponseHead;
import org.edf.hifox.core.datatransfer.support.ResponseMessage;
import org.edf.hifox.core.exception.ServiceException;
import org.edf.hifox.core.exception.UncertainException;
import org.edf.hifox.core.handler.Handler;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;
import org.edf.hifox.core.reqinfo.InboundRequestInfo;
import org.edf.hifox.core.util.DataConvertUtil;
import org.edf.hifox.core.util.ExceptionUtil;
import org.edf.hifox.core.util.MessageUtil;
import org.edf.hifox.core.util.SpringContextUtil;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class InboundExceptionHandler implements Handler<InboundRequestInfo> {
	private static final Logger logger = LoggerFactory.getLogger(InboundExceptionHandler.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handle(InboundRequestInfo data, Invocation invocation) {
		try {
			invocation.invoke(data);
		} catch (Throwable t) {
			logger.error(LogCodeConstant.SYS00009, t);
			ResponseMessage respMsg = new ResponseMessage();
			ResponseHead respHead = new ResponseHead();
			respMsg.setHead(respHead);
			
			RequestHead reqHead = MessageUtil.getRequestHead();
			
			respHead.setSysRespNodeId(MessageUtil.getNodeId());
			respHead.setSysTargetNodeId(reqHead.getSysReqNodeId());
			respHead.setSysEventTraceId(reqHead.getSysEventTraceId());
			respHead.setSysRecvDatetime(DateFormatUtils.format(data.getReceiveTime(), FormatConstant.YMDHMSS));
			respHead.setSysRespDatetime(DateFormatUtils.format(new Date(), FormatConstant.YMDHMSS));
			
			ServiceException se;
			if((se = ExceptionUtil.findServiceException(t)) != null) {
				respHead.setSysRespStatus((se instanceof UncertainException) ? RespStatusConstant.UNCERTAIN : RespStatusConstant.FAILURE);
				respHead.setSysRespCode(se.getCode());
				String respDesc = SpringContextUtil.getMessageNonstrict(se.getCode(), se.getParameters(), reqHead.getSysLanguage(), reqHead.getSysCountry());
				respHead.setSysRespDesc(respDesc);
			} else {
				respHead.setSysRespStatus(RespStatusConstant.UNCERTAIN);
				respHead.setSysRespCode(ErrorCodeConstant.E0001S001);
				String respDesc = SpringContextUtil.getMessageNonstrict(ErrorCodeConstant.E0001S001, reqHead.getSysLanguage(), reqHead.getSysCountry());
				respHead.setSysRespDesc(respDesc);
			}
			
			SwapAreaUtil.setInboundResponseMessage((Message)respMsg);
			
			String respMsgStr = DataConvertUtil.convert(ConvertConstant.EXCEPTION_MAPPING_ID, respMsg);
			SwapAreaUtil.setInboundResponseMsgStr(respMsgStr);
		}
		
	}

}
