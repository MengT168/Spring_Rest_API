package com.meng.test.API.Repostitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meng.test.API.Entity.Brand;
import com.meng.test.API.Entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
	List<Brand> findByBrandId(Integer id);
}
