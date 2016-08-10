package org.edf.hifox.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import bsh.EvalError;
import bsh.Interpreter;

/**
 * 
 * @author WangYang
 *
 */
public class BeanShellUtil {
	
	public static <T> T eval(String shell) {
		return eval(new Interpreter(), shell);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T eval(Interpreter interpreter, String shell) {
		try {
			return (T)interpreter.eval(shell);
		} catch (EvalError e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> T eval(Map<String, Object> param, String shell) {
		return eval(new Interpreter(), param, shell);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T eval(Interpreter interpreter, Map<String, Object> param, String shell) {
		Set<Entry<String, Object>> set = param.entrySet();
		try {
			for(Entry<String, Object> entry : set) {
				interpreter.set(entry.getKey(), entry.getValue());
			}
			return (T)interpreter.eval(shell);
		} catch (EvalError e) {
			throw new RuntimeException(e);
		}
	}
	
}
