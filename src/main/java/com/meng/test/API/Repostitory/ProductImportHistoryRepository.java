package com.meng.test.API.Repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.meng.test.API.Entity.ProductImportHistory;

@Repository
public interface ProductImportHistoryRepository extends JpaRepository<ProductImportHistory, Integer> ,JpaSpecificationExecutor<ProductImportHistory> {

}
