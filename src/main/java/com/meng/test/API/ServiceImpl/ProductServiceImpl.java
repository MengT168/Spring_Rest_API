package com.meng.test.API.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.meng.test.API.DTO.ProductImportDTO;
import com.meng.test.API.Entity.Product;
import com.meng.test.API.Entity.ProductImportHistory;
import com.meng.test.API.Exception.ResourceNotFoundException;
import com.meng.test.API.Mapper.ProductMapper;
import com.meng.test.API.Repostitory.ProductImportHistoryRepository;
import com.meng.test.API.Repostitory.ProductRepository;
import com.meng.test.API.Service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper mapper;
	private final ProductImportHistoryRepository historyRepository;
	
	@Override
	public Product createProduct(Product product) {
		String name = "%s %s".formatted(product.getModel().getName(),product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getById(Integer id) {
		Product product = productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product", id));
		return product;
	}

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public void importProduct(ProductImportDTO dto) {
		//update product
			Product product = getById(dto.getProductId());
			int availableUnit = 0;
			if(product.getAvailableUnit() != null) {
				availableUnit = product.getAvailableUnit();
			}
			product.setAvailableUnit(availableUnit + dto.getImportUnit());
			productRepository.save(product);
		//save product import history
			ProductImportHistory productImportHistory = mapper.productImportHistory(dto, product);
			historyRepository.save(productImportHistory);
	}

	@Override
	public void setSalePrice(Integer id, BigDecimal price) {
		Product product = getById(id);
		product.setSalePrice(price);
		productRepository.save(product);
	}

}
