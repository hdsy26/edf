package org.edf.hifox.core.util;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.NoSuchMessageException;

/**
 * 
 * @author WangYang
 *
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;
    
    public static ApplicationContext getContext() {
    	return context;
    }
    
    
    @SuppressWarnings("unchecked")
	public static <E> E getBean(String beanName, Class<E> clazz) {
    	return (E)context.getBean(beanName);
    }
    
    
    public static String getMessage(String code) throws NoSuchMessageException {
    	return context.getMessage(code, null, Locale.getDefault());
    }
    
    
    public static String getMessage(String code, Locale locale) throws NoSuchMessageException {
    	return context.getMessage(code, null, locale);
    }
    
    
    public static String getMessage(String code, String language, String country) throws NoSuchMessageException {
    	return context.getMessage(code, null, new Locale(language, country));
    }
    
    
    public static String getMessageNonstrict(String code, String language, String country) throws NoSuchMessageException {
    	if(StringUtils.isEmpty(language) || StringUtils.isEmpty(country))
    		return context.getMessage(code, null, Locale.getDefault());
    	else
    		return context.getMessage(code, null, new Locale(language, country));
    }
    
    
    public static String getMessage(String code, Object[] arg) throws NoSuchMessageException {
    	return context.getMessage(code, arg, Locale.getDefault());
    }
    
    
    public static String getMessage(String code, Object[] arg, Locale locale) throws NoSuchMessageException {
    	return context.getMessage(code, arg, locale);
    }
    
    
    public static String getMessage(String code, Object[] arg, String language, String country) throws NoSuchMessageException {
    	return context.getMessage(code, arg, new Locale(language, country));
    }
    
    
    public static String getMessageNonstrict(String code, Object[] arg, String language, String country) throws NoSuchMessageException {
    	if(StringUtils.isEmpty(language) || StringUtils.isEmpty(country))
    		return context.getMessage(code, arg, Locale.getDefault());
    	else
    		return context.getMessage(code, arg, new Locale(language, country));
    }
    
    
    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
    	SpringContextUtil.context = context;
    }
    
    
}