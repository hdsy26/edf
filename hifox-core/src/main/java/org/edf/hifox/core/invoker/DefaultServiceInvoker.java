package org.edf.hifox.core.invoker;

import java.lang.reflect.Method;
import java.util.List;

import org.edf.hifox.core.constant.ErrorCodeConstant;
import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.exception.FailureException;
import org.edf.hifox.core.register.service.registry.ServiceRegistry;
import org.edf.hifox.core.register.service.registry.xmlbean.Argument;
import org.edf.hifox.core.register.service.registry.xmlbean.ServiceDef;
import org.edf.hifox.core.reqinfo.InboundRequestInfo;
import org.edf.hifox.core.util.SpringContextUtil;
import org.edf.hifox.core.util.SwapAreaUtil;

/**
 * 
 * @author WangYang
 *
 */
public class DefaultServiceInvoker implements Invoker<Body, InboundRequestInfo> {
	
	private ServiceRegistry registry;

	public void setRegistry(ServiceRegistry registry) {
		this.registry = registry;
	}

	@Override
	public Body invoke(InboundRequestInfo data) {
		String serviceId = data.getServiceId();
		
		ServiceDef serviceDef = registry.getMeta(serviceId);
		if(serviceDef == null)
			throw new FailureException(ErrorCodeConstant.E0001S002, new Object[]{serviceId});
		
		String beanId = serviceDef.getBeanId();
		Object service = SpringContextUtil.getBean(beanId, Object.class);
		
		List<Argument> arguments = serviceDef.getArguments();
		Class<?>[] argTypes = new Class[arguments.size()];
		Object[] args = new Object[arguments.size()];
		int i = 0;
		for(Argument argument : arguments) {
			try {
				argTypes[i] = Class.forName(argument.getType());
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			args[i] = SwapAreaUtil.getValue(argument.getExpression());
		}
		
		String methodName = serviceDef.getMethodName();
		Class<?> clazz = service.getClass();
		Body result = null;
		try {
			Method method = clazz.getMethod(methodName, argTypes);
			result = (Body)method.invoke(service, args);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
