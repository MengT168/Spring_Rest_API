package com.meng.test.API.ServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.meng.test.API.DTO.ProductSoldDTO;
import com.meng.test.API.DTO.SaleDTO;
import com.meng.test.API.Entity.Product;
import com.meng.test.API.Entity.Sale;
import com.meng.test.API.Entity.Sale_detail;
import com.meng.test.API.Exception.ApiException;
import com.meng.test.API.Exception.ResourceNotFoundException;
import com.meng.test.API.Repostitory.ProductRepository;
import com.meng.test.API.Repostitory.SaleDetailRepository;
import com.meng.test.API.Repostitory.SaleRepository;
import com.meng.test.API.Service.ProductService;
import com.meng.test.API.Service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

	private final ProductRepository productRepository;
	private final ProductService productService;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;

	@Override
	public void sell(SaleDTO saleDTO) {
		List<Integer> productId = saleDTO.getProducts().stream().map(ProductSoldDTO::getProductId).toList();
		productId.forEach(productService::getById);
		List<Product> product = productRepository.findAllById(productId);
		Map<Integer, Product> productMap = product.stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		saleDTO.getProducts().forEach(Ps -> {
			Product products = productMap.get(Ps.getProductId());
			if (products.getAvailableUnit() < Ps.getNumberOfUnt()) {
				throw new ApiException(HttpStatus.BAD_REQUEST,
						"Product %s not enough stock".formatted(products.getName()));
			}
		});

		saleDTO.getProducts().forEach(P -> {
			Product products = productMap.get(P.getProductId());
			if (products.getSalePrice() == null) {
				throw new ApiException(HttpStatus.BAD_REQUEST,
						"Product %s don't have sale price".formatted(products.getName()));
			}
			Sale sale = new Sale();
			sale.setSold_date(saleDTO.getSoldDate());
			sale.setStatus(true);
			saleRepository.save(sale);

			Sale_detail sale_detail = new Sale_detail();
			sale_detail.setProduct(products);
			sale_detail.setSale(sale);
			sale_detail.setUnit(P.getNumberOfUnt());
			sale_detail.setAmount(products.getSalePrice());
			saleDetailRepository.save(sale_detail);

			products.setAvailableUnit(products.getAvailableUnit() - P.getNumberOfUnt());
			productRepository.save(products);
		});

	}

	@Override
	public Sale getById(Integer saleId) {
		Sale sale = saleRepository.findById(saleId).orElseThrow(() -> new ResourceNotFoundException("Sale", saleId));
		return sale;
	}

	@Override
	public void cancelSale(Integer saleId) {
		Sale sale = getById(saleId);
		sale.setStatus(false);
		saleRepository.save(sale);

		List<Sale_detail> saleDetail = saleDetailRepository.getBySaleId(saleId);
		List<Integer> productId = saleDetail.stream().map(P -> P.getProduct().getId()).toList();
		List<Product> products = productRepository.findAllById(productId);

		Map<Integer, Product> productMap = products.stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		saleDetail.forEach(Sd -> {
			Product product = productMap.get(Sd.getProduct().getId());
			product.setAvailableUnit(product.getAvailableUnit() + Sd.getUnit());
			productRepository.save(product);
		});

	}

}
