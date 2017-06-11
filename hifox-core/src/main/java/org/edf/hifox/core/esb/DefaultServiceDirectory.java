package org.edf.hifox.core.esb;

import java.util.Map;

import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.constant.LogCodeConstant;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.log.Logger;
import org.edf.hifox.core.log.LoggerFactory;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultServiceDirectory implements ServiceDirectory<Map<String, String>> {
	private static final Logger logger = LoggerFactory.getLogger(DefaultServiceDirectory.class);
	
	private Map<String, Map<String, String>> dir;
	
	public void setDir(Map<String, Map<String, String>> dir) {
		this.dir = dir;
	}
	

	@Override
	public Map<String, String> lookup(String serviceId) {
		Map<String, String> dirItem = dir.get(serviceId);
		
		if(dirItem == null)
			throw new FailureException(ErrorCodeConstant.E0001S005, new Object[]{serviceId});
		
		logger.info(LogCodeConstant.REG00014, new Object[]{dirItem});
		
		return dirItem;
	}

}
