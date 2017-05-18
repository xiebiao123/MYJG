package com.soshow.ssi.common.enums.base;

public enum StatusEnum  implements ErrorEnum{
	/** 操作成功  */
	SUCCESS("0", "操作成功"),
	/** 操作失败 */
	FAIL("1", "操作失败");

	private final String code;
	private final String description;

	private StatusEnum(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}