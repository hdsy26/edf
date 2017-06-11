package org.edf.hifox.core.log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author WangYang
 *
 */
public class LoggerFactory {
	private static Map<org.slf4j.Logger, Logger> map = new HashMap<org.slf4j.Logger, Logger>();

	public static Logger getLogger(String name) {
		org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(name);
		
		Logger logger = map.get(slf4jLogger);

		if (logger == null) {
			synchronized (LoggerFactory.class) {
				if (logger == null) {
					logger = new I18nLogger(slf4jLogger);
					map.put(slf4jLogger, logger);
				}
			}
		}

		return logger;
	}

	public static Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}
	
	public static Class<? extends org.slf4j.Logger> getLoggerType() {
		if(map.isEmpty())
			return null;
		Set<org.slf4j.Logger> set = map.keySet();
		return set.iterator().next().getClass();
	}

}
