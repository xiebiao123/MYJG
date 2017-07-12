package com.soshow.ssi.common.constant;
/**
 * 公共常量类
 */
public class CommonConstant {
	/**
	 * transaction ID
	 */
    public final static String MDC_LOG_KEY_TXN = "txn";
    /**
     * IP地址
     */
    public final static String MDC_LOG_KEY_IP = "ip";
	/**
	 * 账号-手机号
	 */
    public final static String MDC_LOG_KEY_ACCOUNT = "account";
    /**
     * 用户uid
     */
    public final static String MDC_LOG_KEY_UID = "uid";

	/**
	 * 一天的秒数
	 */
	public final static int DAYS_SECONDS = 60 * 60 * 24;

	/**
	 * 一天的豪秒数
	 */
	public final static int DAYS_MILLSECONDS = 60 * 60 * 24 * 1000;
	
	public final  static int PAGE_SIZE = 10;
	
	public final  static int PAGE_NO = 1;
}
