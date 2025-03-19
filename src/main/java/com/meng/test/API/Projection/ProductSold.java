package com.meng.test.API.Projection;

import java.math.BigDecimal;

public interface ProductSold {
	Long getProductId();
	String getProductName();
	Integer getTotalUnit();
	BigDecimal getTotalAmount();
}
