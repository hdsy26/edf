package org.edf.hifox.core.register.channel.registry.xmlbean;

/**
 * 
 * @author WangYang
 *
 */
public class HttpEndpointDef {
	
	private int maxTotal;
	private int maxPerRoute;
	private int connectTimeout;
	private int soTimeout;
	private String reqCharset;
	private String respCharset;
	private String contentCharset;
	private Integer retryCount = 0;
	
	public int getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}
	public int getMaxPerRoute() {
		return maxPerRoute;
	}
	public void setMaxPerRoute(int maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getSoTimeout() {
		return soTimeout;
	}
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}
	public String getReqCharset() {
		return reqCharset;
	}
	public void setReqCharset(String reqCharset) {
		this.reqCharset = reqCharset;
	}
	public String getRespCharset() {
		return respCharset;
	}
	public void setRespCharset(String respCharset) {
		this.respCharset = respCharset;
	}
	public String getContentCharset() {
		return contentCharset;
	}
	public void setContentCharset(String contentCharset) {
		this.contentCharset = contentCharset;
	}
	public Integer getRetryCount() {
		return retryCount;
	}
	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	
}
