package org.edf.hifox.core.datatransfer;

import java.io.Serializable;

/**
 * 
 * @author WangYang
 *
 */
public interface Message<H extends Head, B extends Body> extends Serializable {
	H getHead();

	void setHead(H head);

	B getBody();

	void setBody(B body);
}
