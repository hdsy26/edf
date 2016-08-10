package org.edf.hifox.log;

import java.util.Locale;

import org.edf.hifox.util.SpringContextUtil;
import org.springframework.context.NoSuchMessageException;

/**
 * 
 * @author WangYang
 *
 */
public class I18nLogger implements Logger {
	private org.slf4j.Logger logger;
	
	public I18nLogger(org.slf4j.Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void trace(String code) {
		if(isTraceEnabled())
			logger.trace(getMessage(code));
	}

	@Override
	public void trace(String code, Object[] args) {
		if(isTraceEnabled())
			logger.trace(getMessage(code, args));
	}

	@Override
	public void trace(String code, Throwable t) {
		if(isTraceEnabled())
			logger.trace(getMessage(code), t);
	}

	@Override
	public void trace(String code, Object[] args, Throwable t) {
		if(isTraceEnabled())
			logger.trace(getMessage(code, args), t);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String code) {
		if(isDebugEnabled())
			logger.debug(getMessage(code));
	}

	@Override
	public void debug(String code, Object[] args) {
		if(isDebugEnabled())
			logger.debug(getMessage(code, args));
	}

	@Override
	public void debug(String code, Throwable t) {
		if(isDebugEnabled())
			logger.debug(getMessage(code), t);
	}

	@Override
	public void debug(String code, Object[] args, Throwable t) {
		if(isDebugEnabled())
			logger.debug(getMessage(code, args), t);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void info(String code) {
		if(isInfoEnabled())
			logger.info(getMessage(code));
	}

	@Override
	public void info(String code, Object[] args) {
		if(isInfoEnabled())
			logger.info(getMessage(code, args));
	}

	@Override
	public void info(String code, Throwable t) {
		if(isInfoEnabled())
			logger.info(getMessage(code), t);
	}

	@Override
	public void info(String code, Object[] args, Throwable t) {
		if(isInfoEnabled())
			logger.info(getMessage(code, args), t);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void warn(String code) {
		if(isWarnEnabled())
			logger.warn(getMessage(code));
	}

	@Override
	public void warn(String code, Object[] args) {
		if(isWarnEnabled())
			logger.warn(getMessage(code, args));
	}

	@Override
	public void warn(String code, Throwable t) {
		if(isWarnEnabled())
			logger.warn(getMessage(code), t);
	}

	@Override
	public void warn(String code, Object[] args, Throwable t) {
		if(isWarnEnabled())
			logger.warn(getMessage(code, args), t);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public void error(String code) {
		if(isErrorEnabled())
			logger.error(getMessage(code));
	}

	@Override
	public void error(String code, Object[] args) {
		if(isErrorEnabled())
			logger.error(getMessage(code, args));
	}

	@Override
	public void error(String code, Throwable t) {
		if(isErrorEnabled())
			logger.error(getMessage(code), t);
	}

	@Override
	public void error(String code, Object[] args, Throwable t) {
		if(isErrorEnabled())
			logger.error(getMessage(code, args), t);
	}
	
	private String getMessage(String code) {
		String msg = null;
		try {
			msg = SpringContextUtil.getMessage(code, Locale.getDefault());
		} catch (NoSuchMessageException e) {
			logger.warn(e.getMessage());
		}
		return msg;
	}
	
	private String getMessage(String code, Object[] args) {
		String msg = null;
		try {
			msg = SpringContextUtil.getMessage(code, args, Locale.getDefault());
		} catch (NoSuchMessageException e) {
			logger.warn(e.getMessage());
		}
		return msg;
	}
	
}
