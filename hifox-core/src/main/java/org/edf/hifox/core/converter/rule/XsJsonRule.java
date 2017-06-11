package org.edf.hifox.core.converter.rule;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author WangYang
 *
 */
public class XsJsonRule implements Rule {
	
	private XStream core;
	
	public XsJsonRule(XStream core) {
		super();
		this.core = core;
	}

	public XStream getCore() {
		return core;
	}

	public void setCore(XStream core) {
		this.core = core;
	}
	
	
}
