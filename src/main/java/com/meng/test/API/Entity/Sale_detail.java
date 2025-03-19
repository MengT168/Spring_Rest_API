package com.meng.test.API.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "saleDetails")
public class Sale_detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_detail_id")
	private int id;
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	@Column(name = "amount")
	private BigDecimal amount;
	private Integer unit;
}
