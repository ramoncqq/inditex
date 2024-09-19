package com.inditex.prices.infrastructure.output.db.repository;

import com.inditex.prices.infrastructure.output.db.entity.PriceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceModel, Long>, JpaSpecificationExecutor<PriceModel> {

}
