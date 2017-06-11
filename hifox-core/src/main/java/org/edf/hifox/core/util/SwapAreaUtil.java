package org.edf.hifox.core.util;

import java.util.Map;

import org.edf.hifox.core.constant.SwapAreaConstant;
import org.edf.hifox.core.datatransfer.Body;
import org.edf.hifox.core.datatransfer.Head;
import org.edf.hifox.core.datatransfer.Message;
import org.edf.hifox.core.reqinfo.InboundRequestInfo;
import org.edf.hifox.core.reqinfo.OutboundRequestInfo;
import org.edf.hifox.core.swaparea.SwapArea;
import org.edf.hifox.core.swaparea.manager.SwapAreaManager;

/**
 * 
 * @author WangYang
 *
 */
public final class SwapAreaUtil {

	/**
	 * 内部数据交换区管理器
	 */
	private static SwapAreaManager swapAreaManager;
	
	private SwapAreaUtil() {
		
	}

	public static void setSwapAreaManager(SwapAreaManager swapAreaManager) {
		SwapAreaUtil.swapAreaManager = swapAreaManager;
	}
	

	/**
	 * 创建当前线程的内部数据交换区
	 * <p>
	 * 
	 * @return 当前线程的内部数据交换区
	 */
	public static SwapArea buildNewSwapArea() {
		return swapAreaManager.buildNewSwapArea();
	}

	/**
	 * 获取当前线程内部数据交换区
	 * <p>
	 * 
	 * @return 当前线程内部数据交换区
	 */
	public static SwapArea getCurrentSwapArea() {
		return swapAreaManager.getCurrentSwapArea();
	}

	/**
	 * 释放当前线程内部数据交换区
	 * <p>
	 * 
	 * @return 当前线程内部数据交换区
	 */
	public static SwapArea releaseCurrentSwapArea() {
		return swapAreaManager.releaseCurrentSwapArea();

	}

	/**
	 * 获取当前线程内部数据交换区中指定路径中的某个域的值
	 * <p>
	 * 
	 * @param path
	 *            路径表达式
	 * @return 返回当前线程内部数据交换区中指定路径的域的值
	 */
	public static Object getValue(String path) {
		SwapArea currentSwapArea = getCurrentSwapArea();
		if (currentSwapArea == null) {
			return null;
		}
		return currentSwapArea.getValue(path);
	}

	/**
	 * 获取当前线程内部数据交换区中指定路径中的某个域的指定类型值
	 * <p>
	 * 
	 * @param path
	 *            路径表达式
	 * @param clazz
	 * @return 返回当前线程内部数据交换区中指定路径的域的值
	 */
	public static <T> T getValue(String path, Class<T> clazz) {
		SwapArea currentSwapArea = getCurrentSwapArea();
		if (currentSwapArea == null) {
			return null;
		}
		return (T) currentSwapArea.getValue(path, clazz);
	}

	/**
	 * 根据指定的路径表达式，设置当前线程内部数据交换区中对应域的值
	 * <p>
	 * 
	 * @param path
	 *            路径表达式
	 * @param value
	 *            值
	 */
	public static void setValue(String path, Object value) {
		SwapArea currentSwapArea = getCurrentSwapArea();
		if (currentSwapArea == null)
			currentSwapArea = buildNewSwapArea();
		currentSwapArea.setValue(path, value);
	}
	
	public static void setInboundRequestInfo(InboundRequestInfo info) {
		setValue(SwapAreaConstant.INBOUND_REQUEST_INFO, info);
	}

	public static InboundRequestInfo getInboundRequestInfo() {
		return getValue(SwapAreaConstant.INBOUND_REQUEST_INFO, InboundRequestInfo.class);
	}
	
	public static void setInboundServiceId(String serviceId) {
		setValue(SwapAreaConstant.INBOUND_SERVICE_ID, serviceId);
	}

	public static String getInboundServiceId() {
		return getValue(SwapAreaConstant.INBOUND_SERVICE_ID, String.class);
	}
	
	public static String getInboundEventTraceId() {
		return getValue(SwapAreaConstant.INBOUND_EVENT_TRACE_ID, String.class);
	}
	
	public static void setInboundEventTraceId(String eventTraceId) {
		setValue(SwapAreaConstant.INBOUND_EVENT_TRACE_ID, eventTraceId);
	}
	
	public static void setInboundReqUsername(String username) {
		setValue(SwapAreaConstant.INBOUND_REQ_USERNAME, username);
	}

	public static String getInboundReqUsername() {
		return getValue(SwapAreaConstant.INBOUND_REQ_USERNAME, String.class);
	}

	public static void setInboundRequestMessage(Message<? extends Head, ? extends Body> reqMsg) {
		setValue(SwapAreaConstant.INBOUND_REQUEST_MESSAGE, reqMsg);
	}

	@SuppressWarnings("unchecked")
	public static Message<? extends Head, ? extends Body> getInboundRequestMessage() {
		return getValue(SwapAreaConstant.INBOUND_REQUEST_MESSAGE, Message.class);
	}

	public static void setInboundResponseMessage(Message<? extends Head, ? extends Body> respMsg) {
		setValue(SwapAreaConstant.INBOUND_RESPONSE_MESSAGE, respMsg);
	}

	@SuppressWarnings("unchecked")
	public static Message<Head, Body> getInboundResponseMessage() {
		return getValue(SwapAreaConstant.INBOUND_RESPONSE_MESSAGE, Message.class);
	}
	
	public static void setInboundResponseMsgStr(String respMsgStr) {
		setValue(SwapAreaConstant.INBOUND_RESPONSE_MESSAGE_STRING, respMsgStr);
	}

	public static String getInboundResponseMsgStr() {
		return getValue(SwapAreaConstant.INBOUND_RESPONSE_MESSAGE_STRING, String.class);
	}
	
	public static String getInboundLanguage() {
		return getValue(SwapAreaConstant.INBOUND_LANGUAGE, String.class);
	}
	
	public static void setInboundLanguage(String language) {
		setValue(SwapAreaConstant.INBOUND_LANGUAGE, language);
	}
	
	public static String getInboundCountry() {
		return getValue(SwapAreaConstant.INBOUND_COUNTRY, String.class);
	}
	
	public static void setInboundCountry(String country) {
		setValue(SwapAreaConstant.INBOUND_COUNTRY, country);
	}
	
	public static void setOutboundRequestInfo(OutboundRequestInfo info) {
		setValue(SwapAreaConstant.OUTBOUND_REQUEST_INFO, info);
	}

	public static OutboundRequestInfo getOutboundRequestInfo() {
		return getValue(SwapAreaConstant.OUTBOUND_REQUEST_INFO, OutboundRequestInfo.class);
	}
	
	public static void setOutboundServiceId(String serviceId) {
		setValue(SwapAreaConstant.OUTBOUND_SERVICE_ID, serviceId);
	}

	public static String getOutboundServiceId() {
		return getValue(SwapAreaConstant.OUTBOUND_SERVICE_ID, String.class);
	}
	
	public static void setOutboundReqUsername(String username) {
		setValue(SwapAreaConstant.OUTBOUND_REQ_USERNAME, username);
	}

	public static String getOutboundReqUsername() {
		return getValue(SwapAreaConstant.OUTBOUND_REQ_USERNAME, String.class);
	}
	
	public static void setOutboundRequestMessage(Message<? extends Head, ? extends Body> reqMsg) {
		setValue(SwapAreaConstant.OUTBOUND_REQUEST_MESSAGE, reqMsg);
	}

	@SuppressWarnings("unchecked")
	public static Message<? extends Head, ? extends Body> getOutboundRequestMessage() {
		return getValue(SwapAreaConstant.OUTBOUND_REQUEST_MESSAGE, Message.class);
	}
	
	public static void setOutboundResponseMessage(Message<? extends Head, ? extends Body> respMsg) {
		setValue(SwapAreaConstant.OUTBOUND_RESPONSE_MESSAGE, respMsg);
	}

	@SuppressWarnings("unchecked")
	public static Message<Head, Body> getOutboundResponseMessage() {
		return getValue(SwapAreaConstant.OUTBOUND_RESPONSE_MESSAGE, Message.class);
	}
	
	public static void setOutboundResponseMsgStr(String respMsgStr) {
		setValue(SwapAreaConstant.OUTBOUND_RESPONSE_MESSAGE_STRING, respMsgStr);
	}

	public static String getOutboundResponseMsgStr() {
		return getValue(SwapAreaConstant.OUTBOUND_RESPONSE_MESSAGE_STRING, String.class);
	}
	
	public static String getOutboundLanguage() {
		return getValue(SwapAreaConstant.OUTBOUND_LANGUAGE, String.class);
	}
	
	public static void setOutboundLanguage(String language) {
		setValue(SwapAreaConstant.OUTBOUND_LANGUAGE, language);
	}
	
	public static String getOutboundCountry() {
		return getValue(SwapAreaConstant.OUTBOUND_COUNTRY, String.class);
	}
	
	public static void setOutboundCountry(String country) {
		setValue(SwapAreaConstant.OUTBOUND_COUNTRY, country);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getOutboundServiceDirItem() {
		return getValue(SwapAreaConstant.OUTBOUND_SERVICE_DIR_ITEM, Map.class);
	}
	
	public static void setOutboundServiceDirItem(Map<String, String> service) {
		setValue(SwapAreaConstant.OUTBOUND_SERVICE_DIR_ITEM, service);
	}
	
	public static String getEncryptedText() {
		return getValue(SwapAreaConstant.ENCRYPTED_TEXT, String.class);
	}
	
	public static void setEncryptedText(String encryptedText) {
		setValue(SwapAreaConstant.ENCRYPTED_TEXT, encryptedText);
	}
	
	public static String getDecryptedText() {
		return getValue(SwapAreaConstant.DECRYPTED_TEXT, String.class);
	}
	
	public static void setDecryptedText(String decryptedText) {
		setValue(SwapAreaConstant.DECRYPTED_TEXT, decryptedText);
	}
	
	public static String getObjectUniqueMark() {
		return getValue(SwapAreaConstant._OBJECT_UNIQUE_MARK, String.class);
	}
	
	public static void setObjectUniqueMark(String objectIdName) {
		setValue(SwapAreaConstant._OBJECT_UNIQUE_MARK, objectIdName);
	}
	
	public static byte[] getEncryptedRandomkey() {
		return getValue(SwapAreaConstant.ENCRYPTED_RANDOMKEY, byte[].class);
	}
	
	public static void setEncryptedRandomkey(byte[] randomkey) {
		setValue(SwapAreaConstant.ENCRYPTED_RANDOMKEY, randomkey);
	}

}
