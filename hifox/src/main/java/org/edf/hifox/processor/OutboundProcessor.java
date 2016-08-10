package org.edf.hifox.processor;

import org.edf.hifox.chain.Chain;
import org.edf.hifox.chain.selector.ChainSelector;
import org.edf.hifox.constant.ErrorCodeConstant;
import org.edf.hifox.constant.LogCodeConstant;
import org.edf.hifox.constant.RespStatusConstant;
import org.edf.hifox.datatransfer.Body;
import org.edf.hifox.datatransfer.Head;
import org.edf.hifox.datatransfer.Message;
import org.edf.hifox.datatransfer.support.ResponseHead;
import org.edf.hifox.exception.FailureException;
import org.edf.hifox.log.Logger;
import org.edf.hifox.log.LoggerFactory;
import org.edf.hifox.reqinfo.OutboundRequestInfo;
import org.edf.hifox.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class OutboundProcessor implements Processor<Object, OutboundRequestInfo> {

	private static final Logger logger = LoggerFactory.getLogger(OutboundProcessor.class);

	private ChainSelector selector;

	public void setSelector(ChainSelector selector) {
		this.selector = selector;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public Object process(OutboundRequestInfo data) {
		logger.info(LogCodeConstant.SYS00004, new Object[]{data.getRequestMessage().getClass().getName()});
		
		Chain chain = selector.select(data);
		chain.doChain(data);
		
		Object respMsg;
		if(data.getRequestMessage() instanceof Message) {
			respMsg = SwapAreaUtil.getOutboundResponseMessage();
			ResponseHead head = (ResponseHead)((Message<Head, Body>)respMsg).getHead();
			String respStatus = head.getSysRespStatus();
			if(data.isCheckStatus() && !RespStatusConstant.SUCCESS.equals(respStatus))
				throw new FailureException(ErrorCodeConstant.E0001S019, new Object[]{respStatus, head.getSysRespCode(), head.getSysRespDesc()});
		} else {
			respMsg = SwapAreaUtil.getOutboundResponseMsgStr();
		}
		
		logger.info(LogCodeConstant.SYS00005, new Object[]{respMsg.getClass().getName()});
		
		return respMsg;
		
	}

}
