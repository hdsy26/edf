package org.edf.hifox.core.util;

import java.sql.SQLException;

import org.edf.hifox.core.exception.ServiceException;

/**
 * 
 * @author WangYang
 *
 */
public final class ExceptionUtil {
	
	private ExceptionUtil() {
		
	}
	
	public static ServiceException findServiceException(Throwable t) {
		if(t instanceof ServiceException) {
			return (ServiceException)t;
		}
		while ((t = t.getCause()) != null) {
			if(t instanceof ServiceException) {
				return (ServiceException)t;
			}
		}
		return null;
	}
	
	public static SQLException findSQLException(Throwable t) {
		if(t instanceof SQLException) {
			return (SQLException)t;
		}
		while ((t = t.getCause()) != null) {
			if(t instanceof SQLException) {
				return (SQLException)t;
			}
		}
		return null;
	}
	
}
