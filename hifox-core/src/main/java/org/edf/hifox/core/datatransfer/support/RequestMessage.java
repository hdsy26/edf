package org.edf.hifox.core.datatransfer.support;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.datatransfer.Message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author WangYang
 *
 */
@XStreamAlias("MESSAGE")
@XmlRootElement(name="MESSAGE")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestMessage implements Message<RequestHead, Body> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2353594513546952299L;
	
	@XStreamAlias("HEAD")
	@XmlElement(name="HEAD")
	private RequestHead head;
	
	@XStreamAlias("BODY")
	@XmlAnyElement(lax=true)
	private Body body;
	
	public RequestMessage() {
		super();
	}

	public RequestMessage(RequestHead head) {
		super();
		this.head = head;
	}

	public RequestMessage(RequestHead head, Body body) {
		super();
		this.head = head;
		this.body = body;
	}

	@Override
	public RequestHead getHead() {
		return head;
	}
	
	@Override
	public void setHead(RequestHead head) {
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
