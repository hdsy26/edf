package org.edf.hifox.editor;

import java.lang.reflect.Field;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;

import org.edf.hifox.exception.ClassEditException;
import org.edf.hifox.log.I18nLogger;

/**
 * 
 * @author WangYang
 *
 */
public class Log4jClassEditor implements ClassEditor {
	
	private static final String CLASSNAME = "org.slf4j.impl.Log4jLoggerAdapter";
	
	public void edit() {
		try {
			ClassPool cp = ClassPool.getDefault();
			cp.insertClassPath(new ClassClassPath(Log4jClassEditor.class));
			CtClass cc = cp.getCtClass(CLASSNAME);
			CtField field = cc.getDeclaredField("FQCN");
			cc.removeField(field);
			field = CtField.make("public static String FQCN;", cc);
			cc.addField(field);
			Class<?> clazz = cc.toClass();
			Field f = clazz.getDeclaredField("FQCN");
			f.set(clazz, I18nLogger.class.getName());
		} catch (Exception e) {
			throw new ClassEditException(e);
		}
	}
	
}
