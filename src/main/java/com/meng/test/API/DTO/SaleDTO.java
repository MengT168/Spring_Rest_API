package com.meng.test.API.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class SaleDTO {
	private List<ProductSoldDTO> products;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd H:mm:ss")
	private LocalDateTime soldDate;
}
