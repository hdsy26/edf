package org.edf.hifox.core.converter.rule;

import freemarker.template.Template;

/**
 * 
 * @author WangYang
 *
 */
public class FmRule implements Rule {
	private Template core;

	public FmRule(Template core) {
		super();
		this.core = core;
	}

	public Template getCore() {
		return core;
	}

	public void setCore(Template core) {
		this.core = core;
	}
	
	
}
