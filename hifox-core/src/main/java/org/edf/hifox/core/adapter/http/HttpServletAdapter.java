package org.edf.hifox.core.adapter.http;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.handler.log.LogHandler;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;
import org.edf.hifox.core.log.context.LogContext;
import org.edf.hifox.core.processor.Processor;
import org.edf.hifox.core.reqinfo.InboundRequestInfo;
import org.edf.hifox.core.util.StreamUtil;
import org.edf.hifox.core.util.SwapAreaUtil;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author WangYang
 *
 */
public class HttpServletAdapter extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 80171982188248859L;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HttpServletAdapter.class);
	
	private static final String ADAPTERID = "httpService";
	
	private static final String LOGCONTEXT_BEANID = "logContextBeanId";
	
	private LogContext logContext;
	
	private static final String PROCESSOR_BEANID = "processorBeanId";
	
	private Processor<byte[], InboundRequestInfo> processor;
	

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		String logContextBeanId = getInitParameter(LOGCONTEXT_BEANID);
		logContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean(logContextBeanId, LogContext.class);
		
		String processorBeanId = getInitParameter(PROCESSOR_BEANID);
		processor = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean(processorBeanId, Processor.class);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			logContext.putLogId();
			
			InboundRequestInfo requestData = new InboundRequestInfo();
			requestData.setAdapterId(ADAPTERID);
			requestData.setRemoteIp(getClientIpAddr(request));
			requestData.setContent(StreamUtil.readStream(request.getInputStream(), false));
			requestData.setReceiveTime(new Date());
			logger.info(LogCodeConstant.SYS00001, new Object[]{requestData.getRemoteIp(), requestData.getContent().length});
			
			byte[] bytes = processor.process(requestData);
			
			StreamUtil.writeStream(response.getOutputStream(), bytes, false);
			
		} finally {
			SwapAreaUtil.releaseCurrentSwapArea();
			LogHandler.removeLogContext();
			logContext.removeLogId();
		}

	}
	
	private String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
