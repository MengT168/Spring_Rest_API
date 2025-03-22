package com.meng.test.API.Security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PermissionEnum {
	BRAND_WRITE("brand:write"),
	BRAND_READ("brand:read"),
	MODEL_WRITE("model:write"),
	MODEL_READ("model:read"),
	COLOR_WRITE("color:write"),
	COLOR_READ("color:read"),
	PRODUCT_WRITE("product:write"),
	PRODUCT_READ("product:read"),
	REPORT_READ("report:read"),
	EXPENCEREPORT_READ("report:read"),
	PERMISSION_WRITE("permission:write"),
	PERMISSION_READ("permission:read");
	
	private String description;
	
	
	
}
