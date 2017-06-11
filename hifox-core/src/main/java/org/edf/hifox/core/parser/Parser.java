package org.edf.hifox.core.parser;

import org.springframework.core.io.Resource;

/**
 * 
 * @author WangYang
 *
 */
public interface Parser<E> {
	E parse(Resource resource) throws Exception ;
}
