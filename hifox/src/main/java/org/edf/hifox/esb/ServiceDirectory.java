package org.edf.hifox.esb;

/**
 * 
 * @author WangYang
 *
 */
public interface ServiceDirectory<E> {
	E lookup(String serviceId);
}
