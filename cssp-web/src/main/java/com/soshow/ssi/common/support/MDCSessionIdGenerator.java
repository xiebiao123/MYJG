package com.soshow.ssi.common.support;


import org.slf4j.MDC;

/**
 * 重写SessionIdGenerator生成MDC的唯一id的方法，如果当前已有值则使用已有值，没有则产生一个新的id
 */
public class MDCSessionIdGenerator extends SessionIdGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5633531647681355949L;

	/**
	 * 
	 */
	public MDCSessionIdGenerator() {
		super();
	}
	
	/**
	 * 重写方法，如果，当前NDC已设置则返回，没有则产生一个新的id
	 */
	@Override
	public String nextStringIdentifier() {
		String txnId = MDC.get("txn");
		if(txnId == null || "".equals(txnId)) {
			return super.nextStringIdentifier();
		} else {
			return txnId;
		}
	}

}
