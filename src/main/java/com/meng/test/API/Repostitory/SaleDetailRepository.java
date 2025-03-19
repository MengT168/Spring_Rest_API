package com.meng.test.API.Repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meng.test.API.Entity.Sale_detail;

@Repository
public interface SaleDetailRepository extends JpaRepository<Sale_detail, Integer> {

}
