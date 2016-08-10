package org.edf.hifox.channel.endpoint.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.edf.hifox.channel.endpoint.Endpoint;
import org.edf.hifox.constant.LogCodeConstant;
import org.edf.hifox.constant.ServiceDirConstant;
import org.edf.hifox.log.Logger;
import org.edf.hifox.log.LoggerFactory;
import org.edf.hifox.register.channel.registry.xmlbean.HttpEndpointDef;
import org.edf.hifox.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class HttpEndpoint implements Endpoint {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpEndpoint.class);
	
	private HttpEndpointDef httpEndpointDef;
	
	private ThreadSafeClientConnManager tscc;
	private DefaultHttpClient httpClient;

	public void setHttpEndpointDef(HttpEndpointDef httpEndpointDef) {
		this.httpEndpointDef = httpEndpointDef;
		
		tscc = new ThreadSafeClientConnManager();
	    tscc.setMaxTotal(httpEndpointDef.getMaxTotal());
	    tscc.setDefaultMaxPerRoute(httpEndpointDef.getMaxPerRoute());
	    httpClient = new DefaultHttpClient(tscc);
	    httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(httpEndpointDef.getRetryCount(), true));
	    httpClient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, httpEndpointDef.getContentCharset());
	    httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, httpEndpointDef.getConnectTimeout());
	    httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, httpEndpointDef.getSoTimeout());
	    
	}


	@Override
	public String send(String serviceId, String reqMsgStr) {
		return execute(reqMsgStr);
	}
	

	@Override
	public String send(String serviceId, Map<String, String> reqMsgStr) {
		return execute(reqMsgStr);
	}
	
	
	private String execute(String msg) {
		logger.info(LogCodeConstant.SYS00012, new Object[]{msg});
		
	    String respMsg = null;
	    HttpPost httpPost = null;
	    try {
	    	httpPost = new HttpPost(SwapAreaUtil.getOutboundServiceDirItem().get(ServiceDirConstant.HTTP_URL));
	    	StringEntity se = new StringEntity(msg, httpEndpointDef.getReqCharset());
	    	httpPost.setEntity(se);
	    	HttpResponse httpResponse = httpClient.execute(httpPost);
	    	int httpStatus = httpResponse.getStatusLine().getStatusCode();
	    	
	    	HttpEntity httpEntity = httpResponse.getEntity();
	    	if ((httpStatus == 200) && (httpEntity != null)) {
	    		respMsg = EntityUtils.toString(httpEntity, httpEndpointDef.getRespCharset());
	    		logger.info(LogCodeConstant.SYS00013, new Object[]{respMsg});
	    	} else {
	    		throw new RuntimeException("httpStatus:" + httpStatus);
	    	}
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
	    } finally {
	    	if(httpPost != null)
	    		httpPost.abort();
	    }
	    
	    return respMsg;
	}
	
	
	private String execute(Map<String, String> msg) {
		logger.info(LogCodeConstant.SYS00012, new Object[]{msg});
		
	    String respMsg = null;
	    HttpPost httpPost = null;
	    try {
	    	httpPost = new HttpPost(SwapAreaUtil.getOutboundServiceDirItem().get(ServiceDirConstant.HTTP_URL));
	    	List<BasicNameValuePair> formPar = new ArrayList<BasicNameValuePair>();
			for(Entry<String, String> entry : msg.entrySet()) {
				BasicNameValuePair bnvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
				formPar.add(bnvp);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(formPar, httpEndpointDef.getReqCharset()));
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
	    	int httpStatus = httpResponse.getStatusLine().getStatusCode();
	    	
	    	HttpEntity httpEntity = httpResponse.getEntity();
	    	if ((httpStatus == 200) && (httpEntity != null)) {
	    		respMsg = EntityUtils.toString(httpEntity, httpEndpointDef.getRespCharset());
	    		logger.info(LogCodeConstant.SYS00013, new Object[]{respMsg});
	    	} else {
	    		throw new RuntimeException("httpStatus:" + httpStatus);
	    	}
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
		} finally {
			if(httpPost != null)
	    		httpPost.abort();
		}
		return respMsg;
	}
	

	@Override
	protected void finalize() throws Throwable {
		tscc.shutdown();
		super.finalize();
	}
	

}
