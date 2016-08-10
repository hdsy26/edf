# edf
easy development framework

web project web.xml
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
