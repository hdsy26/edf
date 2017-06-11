package org.edf.hifox.security.datatransfer.inbound;

import org.edf.hifox.core.datatransfer.Body;

public class A000S000Req implements Body {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8164453548242567791L;
	
	private String datetime;
	private String type;
	private String text;
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
