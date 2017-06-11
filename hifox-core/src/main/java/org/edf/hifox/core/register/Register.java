package org.edf.hifox.core.register;

import org.springframework.core.io.Resource;

/**
 * 
 * @author WangYang
 *
 */
public interface Register {
	void regist(Resource[] resources) throws Exception;
}
