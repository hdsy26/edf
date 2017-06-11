package org.edf.hifox.core.datatransfer.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.edf.hifox.core.datatransfer.Head;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author WangYang
 *
 */
@XStreamAlias("HEAD")
@XmlRootElement(name="HEAD")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestHead implements Head {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6391881929382033236L;
	
	@XStreamAlias("SYS_REQ_NODE_ID")
	@XmlElement(name="SYS_REQ_NODE_ID")
	private String sysReqNodeId;
	
	@XStreamAlias("SYS_TARGET_NODE_ID")
	@XmlElement(name="SYS_TARGET_NODE_ID")
	private String sysTargetNodeId;
	
	@XStreamAlias("SYS_SERVICE_ID")
	@XmlElement(name="SYS_SERVICE_ID")
	private String sysServiceId;
	
	@XStreamAlias("SYS_SERVICE_TYPE")
	@XmlElement(name="SYS_SERVICE_TYPE")
	private String sysServiceType;
	
	@XStreamAlias("SYS_EVENT_TRACE_ID")
	@XmlElement(name="SYS_EVENT_TRACE_ID")
	private String sysEventTraceId;
	
	@XStreamAlias("SYS_REQ_VERSION")
	@XmlElement(name="SYS_REQ_VERSION")
	private String sysReqVersion;
	
	@XStreamAlias("SYS_REQ_DATETIME")
	@XmlElement(name="SYS_REQ_DATETIME")
	private String sysReqDatetime;
	
	@XStreamAlias("SYS_LANGUAGE")
	@XmlElement(name="SYS_LANGUAGE")
	private String sysLanguage;
	
	@XStreamAlias("SYS_COUNTRY")
	@XmlElement(name="SYS_COUNTRY")
	private String sysCountry;
	
	@XStreamAlias("SYS_REQ_USERNAME")
	@XmlElement(name="SYS_REQ_USERNAME")
	private String sysReqUsername;
	
	public String getSysReqNodeId() {
		return sysReqNodeId;
	}
	public void setSysReqNodeId(String sysReqNodeId) {
		this.sysReqNodeId = sysReqNodeId;
	}
	public String getSysTargetNodeId() {
		return sysTargetNodeId;
	}
	public void setSysTargetNodeId(String sysTargetNodeId) {
		this.sysTargetNodeId = sysTargetNodeId;
	}
	public String getSysServiceId() {
		return sysServiceId;
	}
	public void setSysServiceId(String sysServiceId) {
		this.sysServiceId = sysServiceId;
	}
	public String getSysServiceType() {
		return sysServiceType;
	}
	public void setSysServiceType(String sysServiceType) {
		this.sysServiceType = sysServiceType;
	}
	public String getSysEventTraceId() {
		return sysEventTraceId;
	}
	public void setSysEventTraceId(String sysEventTraceId) {
		this.sysEventTraceId = sysEventTraceId;
	}
	public String getSysReqVersion() {
		return sysReqVersion;
	}
	public void setSysReqVersion(String sysReqVersion) {
		this.sysReqVersion = sysReqVersion;
	}
	public String getSysReqDatetime() {
		return sysReqDatetime;
	}
	public void setSysReqDatetime(String sysReqDatetime) {
		this.sysReqDatetime = sysReqDatetime;
	}
	public String getSysLanguage() {
		return sysLanguage;
	}
	public void setSysLanguage(String sysLanguage) {
		this.sysLanguage = sysLanguage;
	}
	public String getSysCountry() {
		return sysCountry;
	}
	public void setSysCountry(String sysCountry) {
		this.sysCountry = sysCountry;
	}
	public String getSysReqUsername() {
		return sysReqUsername;
	}
	public void setSysReqUsername(String sysReqUsername) {
		this.sysReqUsername = sysReqUsername;
	}
	
}
