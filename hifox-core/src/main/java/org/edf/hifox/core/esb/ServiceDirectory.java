package org.edf.hifox.core.esb;

/**
 * 
 * @author WangYang
 *
 */
public interface ServiceDirectory<E> {
	E lookup(String serviceId);
}
