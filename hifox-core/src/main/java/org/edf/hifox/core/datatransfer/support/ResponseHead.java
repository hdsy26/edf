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
public class ResponseHead implements Head {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6391881929382033236L;
	
	@XStreamAlias("SYS_RESP_NODE_ID")
	@XmlElement(name="SYS_RESP_NODE_ID")
	private String sysRespNodeId;
	
	@XStreamAlias("SYS_TARGET_NODE_ID")
	@XmlElement(name="SYS_TARGET_NODE_ID")
	private String sysTargetNodeId;
	
	@XStreamAlias("SYS_EVENT_TRACE_ID")
	@XmlElement(name="SYS_EVENT_TRACE_ID")
	private String sysEventTraceId;
	
	@XStreamAlias("SYS_RECV_DATETIME")
	@XmlElement(name="SYS_RECV_DATETIME")
	private String sysRecvDatetime;
	
	@XStreamAlias("SYS_RESP_DATETIME")
	@XmlElement(name="SYS_RESP_DATETIME")
	private String sysRespDatetime;
	
	@XStreamAlias("SYS_RESP_STATUS")
	@XmlElement(name="SYS_RESP_STATUS")
	private String sysRespStatus;
	
	@XStreamAlias("SYS_RESP_CODE")
	@XmlElement(name="SYS_RESP_CODE")
	private String sysRespCode;
	
	@XStreamAlias("SYS_RESP_DESC")
	@XmlElement(name="SYS_RESP_DESC")
	private String sysRespDesc;
	
	public String getSysRespNodeId() {
		return sysRespNodeId;
	}
	public void setSysRespNodeId(String sysRespNodeId) {
		this.sysRespNodeId = sysRespNodeId;
	}
	public String getSysTargetNodeId() {
		return sysTargetNodeId;
	}
	public void setSysTargetNodeId(String sysTargetNodeId) {
		this.sysTargetNodeId = sysTargetNodeId;
	}
	public String getSysEventTraceId() {
		return sysEventTraceId;
	}
	public void setSysEventTraceId(String sysEventTraceId) {
		this.sysEventTraceId = sysEventTraceId;
	}
	public String getSysRecvDatetime() {
		return sysRecvDatetime;
	}
	public void setSysRecvDatetime(String sysRecvDatetime) {
		this.sysRecvDatetime = sysRecvDatetime;
	}
	public String getSysRespDatetime() {
		return sysRespDatetime;
	}
	public void setSysRespDatetime(String sysRespDatetime) {
		this.sysRespDatetime = sysRespDatetime;
	}
	public String getSysRespStatus() {
		return sysRespStatus;
	}
	public void setSysRespStatus(String sysRespStatus) {
		this.sysRespStatus = sysRespStatus;
	}
	public String getSysRespCode() {
		return sysRespCode;
	}
	public void setSysRespCode(String sysRespCode) {
		this.sysRespCode = sysRespCode;
	}
	public String getSysRespDesc() {
		return sysRespDesc;
	}
	public void setSysRespDesc(String sysRespDesc) {
		this.sysRespDesc = sysRespDesc;
	}
	
}
