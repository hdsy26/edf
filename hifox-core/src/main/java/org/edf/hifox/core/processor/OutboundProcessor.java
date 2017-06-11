package org.edf.hifox.core.processor;

import org.edf.hifox.core.chain.Chain;
import org.edf.hifox.core.chain.selector.ChainSelector;
import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.constant.RespStatusConstant;
import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.datatransfer.Head;
import org.edf.hifox.core.datatransfer.Message;
import org.edf.hifox.core.datatransfer.support.ResponseHead;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;
import org.edf.hifox.core.reqinfo.OutboundRequestInfo;
import org.edf.hifox.core.util.SwapAreaUtil;

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
