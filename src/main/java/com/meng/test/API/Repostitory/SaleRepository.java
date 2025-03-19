package com.meng.test.API.Repostitory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meng.test.API.Entity.Sale;
import com.meng.test.API.Projection.ProductSold;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
	
	@Query(value = "SELECT product.product_id , product.product_name ,SUM(sale_details.unit) as total_unit ,SUM(sale_details.unit * sale_details.amount) as total_amount FROM `sale_details` \r\n"
			+ "INNER JOIN sale ON sale_details.sale_id = sale.sale_id\r\n"
			+ "INNER JOIN product ON  product.product_id = sale_details.product_id \r\n"
			+ "WHERE date(sale.sold_date) >=:startDate and date(sale.sold_date) <=:endDate AND sale.status = true\r\n"
			+ "GROUP BY sale_details.sale_detail_id",
			nativeQuery = true)
	List<ProductSold> findProductSold(LocalDate startDate , LocalDate endDate);
}
