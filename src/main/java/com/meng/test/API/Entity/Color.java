package com.meng.test.API.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "color")
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "color_id")
	private int id;
	@Column(name = "color_name")
	private String name;
}
