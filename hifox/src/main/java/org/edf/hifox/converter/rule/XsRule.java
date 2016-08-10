package org.edf.hifox.converter.rule;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author WangYang
 *
 */
public class XsRule implements Rule {
	
	private XStream core;
	
	public XsRule(XStream core) {
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
