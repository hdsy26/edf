package org.edf.hifox.util;

import java.sql.SQLException;

import org.edf.hifox.exception.ServiceException;

/**
 * 
 * @author WangYang
 *
 */
public class ExceptionUtil {
	
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
