# edf
easy development framework




web project
web.xml

<context-param>
    <param-name>classEditors</param-name>
    <param-value>org.edf.hifox.editor.Log4jClassEditor</param-value>
  </context-param>
  <listener>
    <listener-class>org.edf.hifox.editor.ClassEditorListener</listener-class>
  </listener>
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>
		classpath*:framework/spring/*-applicationContext.xml
		classpath*:application/spring/*-applicationContext.xml
	</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
  <servlet>
    <display-name>Service</display-name>
    <servlet-name>Service</servlet-name>
    <servlet-class>org.edf.hifox.adapter.http.HttpServletAdapter</servlet-class>
    <init-param>
      <param-name>logContextBeanId</param-name>
      <param-value>log4jContext</param-value>
    </init-param>
    <init-param>
      <param-name>processorBeanId</param-name>
      <param-value>inboundProcessor</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>Service</servlet-name>
    <url-pattern>/service/*</url-pattern>
  </servlet-mapping>




spring config
core-applicationContext.xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-3.2.xsd
		http://www.springframework.org/schema/util
		http://www.springframework.org/schema/util/spring-util-3.2.xsd">
	
	<!-- 属性占位符配置 -->
	<bean id="propertyPlaceholderConfigurer"
		class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer"
		p:ignoreUnresolvablePlaceholders="true">
		<property name="locations">
			<list>
				<value>classpath*:application/*.properties</value>
			</list>
		</property>
	</bean>

	<!-- 国际化组件 -->
	<bean id="messageSource"
		class="org.springframework.context.support.ResourceBundleMessageSource">
		<property name="basenames">
			<list>
				<value>i18n/log/fwlog</value>
				<value>i18n/error/fwerror</value>
				<value>application/i18n/log/log</value>
				<value>application/i18n/error/error</value>
			</list>
		</property>
	</bean>

	<!-- 框架上下文 -->
	<bean id="springContextUtil" class="org.edf.hifox.util.SpringContextUtil" />
	
	<!-- 表达式解析组件 -->
	<bean id="expressionParser" class="org.edf.hifox.expression.spel.SpelExpressionParser" />
	
	<!-- 数据交换区管理器 -->
	<bean id="swapAreaManager" class="org.edf.hifox.swaparea.manager.DefaultSwapAreaManager">
		<constructor-arg>
			<bean class="org.edf.hifox.swaparea.DefaultSwapAreaHolder" />
		</constructor-arg>
		<constructor-arg ref="expressionParser" />
	</bean>
	
	<!-- 数据交换区工具 -->
	<bean class="org.edf.hifox.util.SwapAreaUtil" p:swapAreaManager-ref="swapAreaManager" />
	
	<bean class="org.edf.hifox.util.MessageUtil">
		<property name="nodeId" value="${nodeid}"/>
	</bean>
	
	<bean id="httpServiceInboundChain" class="org.edf.hifox.chain.HandlerChain">
		<property name="handlerList">
			<list>
				<ref bean="inboundSecurityHandler"/>
				<ref bean="inboundTradeHandler"/>
				<ref bean="inboundExceptionHandler"/>
				<ref bean="inboundDataConvertHandler"/>
				<ref bean="inboundSwapAreaHandler"/>
				<ref bean="logConfigHandler"/>
				<ref bean="serviceHandler"/>
			</list>
		</property>
	</bean>
	
	<!-- 链选择器 -->
	<bean id="inboundStackSelector"
		class="org.edf.hifox.chain.selector.DefaultChainSelector"
		p:expressionParser-ref="expressionParser"
		p:defaultChain-ref="httpServiceInboundChain">
		<constructor-arg>
			<map>
				<entry key="adapterId == 'httpService'" value-ref="httpServiceInboundChain" />
			</map>
		</constructor-arg>
	</bean>
	
	<bean id="inboundProcessor" class="org.edf.hifox.processor.InboundProcessor" 
		p:selector-ref="inboundStackSelector" p:requestEncoding="UTF-8" p:responseEncoding="UTF-8" 
		p:serviceIdXmlPath="/MESSAGE/HEAD/SYS_SERVICE_ID" />


	<bean id="httpServiceOutboundChain" class="org.edf.hifox.chain.HandlerChain">
		<property name="handlerList">
			<list>
				<ref bean="outboundSwapAreaHandler"/>
				<ref bean="outboundDataConvertHandler"/>
				<ref bean="outboundSecurityHandler"/>
				<ref bean="channelHandler"/>
			</list>
		</property>
	</bean>
	
	<!-- 链选择器 -->
	<bean id="outboundStackSelector"
		class="org.edf.hifox.chain.selector.DefaultChainSelector"
		p:expressionParser-ref="expressionParser"
		p:defaultChain-ref="httpServiceOutboundChain">
		<constructor-arg>
			<map>
				<entry key="requestMessage instanceof T(org.edf.hifox.datatransfer.Message) 
					or requestMessage instanceof T(java.util.Map) 
					or requestMessage instanceof T(java.lang.String)" value-ref="httpServiceOutboundChain" />
			</map>
		</constructor-arg>
	</bean>
	
	<bean id="outboundProcessor" class="org.edf.hifox.processor.OutboundProcessor" 
		p:selector-ref="outboundStackSelector" />
		
</beans>




register-applicationContext.xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-3.2.xsd">
	
	<bean id="converterConfigParser" class="org.edf.hifox.parser.DefaultParser">
		<property name="ruleResource">
			<value>classpath:rule/converter-rule.xml</value>
		</property>
		<property name="className" value="org.edf.hifox.register.converter.registry.xmlbean.ConverterCfg"/>
	</bean>
	
	<!-- 数据转换注册表 -->
	<bean id="converterRegistry" class="org.edf.hifox.register.converter.registry.ConverterRegistry" />

	<!-- 数据转换注册器 -->
	<bean class="org.edf.hifox.register.converter.ConverterConfigRegister">
		<property name="springContextUtil" ref="springContextUtil"/>
		<property name="parser" ref="converterConfigParser" />
		<property name="registry" ref="converterRegistry" />
		<property name="resources">
			<list>
				<value>classpath*:application/converter/*/*-converter-registry.xml</value>
			</list>
		</property>
	</bean>
	
	<bean id="serviceConfigParser" class="org.edf.hifox.parser.DefaultParser">
		<property name="ruleResource">
			<value>classpath:rule/service-rule.xml</value>
		</property>
		<property name="className" value="org.edf.hifox.register.service.registry.xmlbean.ServiceCfg"/>
	</bean>
	
	<!-- 服务注册表 -->
	<bean id="serviceRegistry" class="org.edf.hifox.register.service.registry.ServiceRegistry" />

	<!-- 服务注册器 -->
	<bean class="org.edf.hifox.register.service.ServiceConfigRegister">
		<property name="springContextUtil" ref="springContextUtil"/>
		<property name="parser" ref="serviceConfigParser" />
		<property name="registry" ref="serviceRegistry" />
		<property name="resources">
			<list>
				<value>classpath*:application/service/*/*-service-registry.xml</value>
			</list>
		</property>
	</bean>
	
	<bean id="channelConfigParser" class="org.edf.hifox.parser.DefaultParser">
		<property name="ruleResource">
			<value>classpath:rule/channel-rule.xml</value>
		</property>
		<property name="className" value="org.edf.hifox.register.channel.registry.xmlbean.ChannelCfg"/>
	</bean>
	
	<!-- 通道注册表 -->
	<bean id="channelRegistry" class="org.edf.hifox.register.channel.registry.ChannelRegistry" />

	<!-- 通道注册器 -->
	<bean class="org.edf.hifox.register.channel.ChannelConfigRegister">
		<property name="springContextUtil" ref="springContextUtil"/>
		<property name="parser" ref="channelConfigParser" />
		<property name="registry" ref="channelRegistry" />
		<property name="resources">
			<list>
				<value>classpath*:application/channel/*-channel-registry.xml</value>
			</list>
		</property>
	</bean>
	
	<bean id="excelConfigParser" class="org.edf.hifox.parser.DefaultParser">
		<property name="ruleResource">
			<value>classpath:rule/excel-rule.xml</value>
		</property>
		<property name="className" value="org.edf.hifox.register.excel.registry.xmlbean.ExcelCfg"/>
	</bean>
	
	<!-- excel注册表 -->
	<bean id="excelRegistry" class="org.edf.hifox.register.excel.registry.ExcelRegistry" />

	<!-- excel注册器 -->
	<bean class="org.edf.hifox.register.excel.ExcelConfigRegister">
		<property name="springContextUtil" ref="springContextUtil"/>
		<property name="parser" ref="excelConfigParser" />
		<property name="registry" ref="excelRegistry" />
		<property name="resources">
			<list>
				<value>classpath*:application/excel/*-excel-registry.xml</value>
			</list>
		</property>
	</bean>
	
	<bean id="defaultConverterManager" class="org.edf.hifox.converter.manager.DefaultConverterManager">
		<property name="registry" ref="converterRegistry" />
	</bean>
	
	<bean class="org.edf.hifox.util.DataConvertUtil">
		<property name="converterManager" ref="defaultConverterManager" />
	</bean>
	
	<bean class="org.edf.hifox.util.OutboundUtil">
		<property name="processor" ref="outboundProcessor" />
	</bean>

</beans>




converter-applicationContext.xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-3.2.xsd">
	
	<bean id="xsBeanToXmlConverter" class="org.edf.hifox.converter.XsBeanToXmlConverter" />
	<bean id="xsXmlToBeanConverter" class="org.edf.hifox.converter.XsXmlToBeanConverter" />
	<bean id="xsRuleCreator" class="org.edf.hifox.converter.creator.XsRuleCreator" />
	
	<bean id="jaxbBeanToXmlConverter" class="org.edf.hifox.converter.JaxbBeanToXmlConverter" />
	<bean id="jaxbXmlToBeanConverter" class="org.edf.hifox.converter.JaxbXmlToBeanConverter" />
	<bean id="jaxbRuleCreator" class="org.edf.hifox.converter.creator.JaxbRuleCreator" />
	
	<bean id="fmBeanToStrConverter" class="org.edf.hifox.converter.FmBeanToStrConverter" />
	<bean id="fmRuleCreator" class="org.edf.hifox.converter.creator.FmRuleCreator" />
	
</beans>




handler-applicationContext.xml

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:c="http://www.springframework.org/schema/c"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context-3.2.xsd">
	
	<bean name="inboundSecurityHandler" class="org.edf.hifox.handler.inbound.InboundSecurityHandler" />
	
	<bean name="inboundTradeHandler" class="com.ule.report.handler.InboundTradeHandler">
		<property name="reqNodeIdXmlPath" value="/MESSAGE/HEAD/SYS_REQ_NODE_ID" />
		<property name="targetNodeIdXmlPath" value="/MESSAGE/HEAD/SYS_TARGET_NODE_ID" />
		<property name="serviceTypeXmlPath" value="/MESSAGE/HEAD/SYS_SERVICE_TYPE" />
		<property name="eventTraceIdXmlPath" value="/MESSAGE/HEAD/SYS_EVENT_TRACE_ID" />
		<property name="tradeHelper" ref="inboundTradeHelper" />
	</bean>
	
	<bean name="inboundExceptionHandler" class="org.edf.hifox.handler.inbound.InboundExceptionHandler" />
	
	<bean name="inboundDataConvertHandler" class="org.edf.hifox.handler.inbound.InboundDataConvertHandler" />
	
	<bean name="inboundSwapAreaHandler" class="org.edf.hifox.handler.inbound.InboundSwapAreaHandler">
		<property name="eventTraceIdPath" value="['_inbound_request_message'].head.sysEventTraceId" />
		<property name="reqUsernamePath" value="['_inbound_request_message'].head.sysReqUsername" />
	</bean>
	
	<bean id="log4jContext" class="org.edf.hifox.log.context.Log4jContext" />
	
	<bean name="logConfigHandler" class="org.edf.hifox.handler.log.LogHandler">
		<property name="contexts">
			<map>
				<entry key="['_log_service_id']" value="['_inbound_request_message'].head.sysServiceId" />
			</map>
		</property>
		<property name="logContext" ref="log4jContext" />
	</bean>
	
	<bean id="defaultServiceInvoker" class="org.edf.hifox.invoker.DefaultServiceInvoker" 
		p:registry-ref="serviceRegistry" />
	<bean name="serviceHandler" class="org.edf.hifox.handler.service.ServiceHandler">
		<property name="invoker" ref="defaultServiceInvoker" />
	</bean>
	
	<bean name="outboundSwapAreaHandler" class="org.edf.hifox.handler.outbound.OutboundSwapAreaHandler">
		<property name="reqUsernamePath" value="['_outbound_request_message'].head.sysReqUsername" />
		<property name="serviceDirectory" ref="defaultServiceDirectory" />
	</bean>
	
	<bean name="outboundDataConvertHandler" class="org.edf.hifox.handler.outbound.OutboundDataConvertHandler" />
	
	<bean name="outboundSecurityHandler" class="org.edf.hifox.handler.outbound.OutboundSecurityHandler" />
	
	<bean id="defaultChannelInvoker" class="org.edf.hifox.invoker.DefaultChannelInvoker"
		p:registry-ref="channelRegistry" />
	<bean name="channelHandler" class="org.edf.hifox.handler.channel.ChannelHandler">
		<property name="invoker" ref="defaultChannelInvoker" />
	</bean>
	
</beans>

