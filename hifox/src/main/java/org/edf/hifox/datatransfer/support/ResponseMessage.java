package org.edf.hifox.datatransfer.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.edf.hifox.datatransfer.Body;
import org.edf.hifox.datatransfer.Message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author WangYang
 *
 */
@XStreamAlias("MESSAGE")
@XmlRootElement(name="MESSAGE")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseMessage implements Message<ResponseHead, Body> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2353594513546952299L;
	
	@XStreamAlias("HEAD")
	@XmlElement(name="HEAD")
	private ResponseHead head;
	
	@XStreamAlias("BODY")
	@XmlAnyElement(lax=true)
	private Body body;
	
	@Override
	public ResponseHead getHead() {
		return head;
	}
	
	@Override
	public void setHead(ResponseHead head) {
		this.head = head;
	}
	
	@Override
	public Body getBody() {
		return body;
	}
	
	@Override
	public void setBody(Body body) {
		this.body = body;
	}
	
	
}
