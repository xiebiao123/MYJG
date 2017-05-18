package com.soshow.ssi.common.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/************************
 * 提取请求参数工具类
 * 
 * @author zhouzhsh
 * 
 */
public class WebUtil {
	private static Logger log = LoggerFactory.getLogger(WebUtil.class);
	
	public final static String RES = "resources";

	/**
	 * 获取Http协议POST的请求内容,不是文件上传可用这种方式
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestStr(HttpServletRequest request) {
		String str = null;
		int contentLen = request.getContentLength();
		if (contentLen > 0) {
			byte[] b = new byte[contentLen];
			DataInputStream dataInputStream = null;
			try {
				dataInputStream = new DataInputStream(request.getInputStream());
				dataInputStream.read(b);
				str = new String(b, "UTF-8");
			} catch (Exception e) {
				log.error("[getRequestStr]",e);
			} finally {
				if (null != dataInputStream) {
					try {
						dataInputStream.close();
					} catch (IOException e) {
						log.error("[dataInputStream close异常]",e);
					}
				}
			}
		}
		return str;
	}

	/****************************
	 * 把参数提取到map中
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getReqParams(HttpServletRequest request) {
		DiskFileItemFactory diFactory = new DiskFileItemFactory();
		Map<String, Object> map = new HashMap<String, Object>();
		ServletFileUpload fileUpload = new ServletFileUpload(diFactory);
		// 判断是否是上传方式的请求
		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if (isMulti) {
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> fileItems = fileUpload.parseRequest(request);
				for (FileItem fileItem : fileItems) {
					String itemName = fileItem.getFieldName();
					if (fileItem.isFormField()) {
						try {
							map.put(itemName, fileItem.getString("UTF-8"));
						} catch (UnsupportedEncodingException e) {
							log.error("[getReqParams]",e);
						}
					} else {
						String fileName = fileItem.getName();// 获取上传文件名,包括路径
						fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);// 从全路径中提取文件名
						long size = fileItem.getSize();
						if ((fileName == null || fileName.equals("")) && size == 0)
							continue;
						map.put(itemName, fileItem);
					}
				}
			} catch (FileUploadException e) {
				log.info("上传文件报错：" , e);
			}

		} else {
			// 直接用putAll
			map.putAll(request.getParameterMap());
		}
		return map;
	}

	/**
	 * 请求地址的IP
	 * 
	 * @param request
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getRequestIP(HttpServletRequest request){
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			if ("127.0.0.1".equals(ip)) {
				try {
					ip = InetAddress.getLocalHost().getHostAddress();
				} catch (UnknownHostException e) {
					log.warn("getRequestIP UnknownHost", e);
				}
			}
		}
		if (null  !=  ip) {
			int index = ip.indexOf(",");
			if (index > 0) {
				ip = ip.substring(0,index);
			}
		}
		return ip;
	}

	/**
	 * 从请求中获取参数map
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getReqPostParams(HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		Map.Entry entry = null;
		String name = "";
		String value = "";
		Iterator paramIr = request.getParameterMap().entrySet().iterator();
		while (paramIr.hasNext()) {
			entry = (Entry) paramIr.next();
			name = (String) entry.getKey();
			Object valueObject = entry.getValue();
			value = "";
			if (valueObject == null) {
				value = "";
			} else if (valueObject instanceof String[]) {
				String[] values = (String[]) valueObject;
				for (int idx = 0; idx < values.length; idx++) {
					value += values[idx] + ",";
				}
				value = value.substring(0, value.length() - 1);// 去掉最后一个,
			} else {
				value = valueObject.toString();
			}
			map.put(name, value);
		}
		return map;
	}
	
	/**
	 * 得到操作的用户IP地址
	 * 
	 * @return
	 */
	public static String getRequestIP() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return getRequestIP(request);
	}
}
