package com.meng.test.API.Filter;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExpenseReportFilter {
	private LocalDate startDate;
	private LocalDate enDate;
}
