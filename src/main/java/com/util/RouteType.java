package com.util;

public enum RouteType {
	START_PLACE(0, "出发站"), MIDDLE_PLACE(1, "途径地"), END_PLACE(2, "目的地");

	RouteType(int typeCode, String description) {
		this.typeCode = typeCode;
		this.description = description;
	}

	private int typeCode;

	private String description;

	public int getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
