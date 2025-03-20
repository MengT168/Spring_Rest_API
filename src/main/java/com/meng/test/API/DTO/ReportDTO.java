package com.meng.test.API.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ReportDTO {

	private int productId;
	private String productName;
	private int totalUnit;
	private BigDecimal totalAmount;
}
