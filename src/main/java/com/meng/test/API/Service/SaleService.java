package com.meng.test.API.Service;

import com.meng.test.API.DTO.SaleDTO;
import com.meng.test.API.Entity.Sale;

public interface SaleService {
	void sell(SaleDTO dto);
	Sale getById(Integer saleId);
	void cancelSale(Integer saleId);
}
