package com.meng.test.API.Service;

import java.math.BigDecimal;
import java.util.List;

import com.meng.test.API.DTO.ProductImportDTO;
import com.meng.test.API.Entity.Product;

public interface ProductService {
	Product createProduct(Product product);
	List<Product> getProducts();
	Product getById(Integer id);
	void importProduct(ProductImportDTO dto);
	void setSalePrice(Integer id , BigDecimal price);
//	Product updateSalePrice(Integer id , )
}
