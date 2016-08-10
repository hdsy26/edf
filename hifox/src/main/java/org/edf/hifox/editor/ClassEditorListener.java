package org.edf.hifox.editor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.edf.hifox.exception.InitializationException;

/**
 * 
 * @author WangYang
 *
 */
public class ClassEditorListener implements ServletContextListener {
	
	private static final String SEPARATOR = ";";

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		String value = arg0.getServletContext().getInitParameter("classEditors");
		String[] classNames = value.split(SEPARATOR);
		ClassEditor classEditor;
		Class<ClassEditor> clazz;
		try {
			for(String className : classNames) {
				clazz = (Class<ClassEditor>) Class.forName(className);
				classEditor = clazz.newInstance();
				classEditor.edit();
			}
		} catch (Exception e) {
			throw new InitializationException(e);
		}
		
	}
	
}
