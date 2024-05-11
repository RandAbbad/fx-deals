package com.progresssoft.repository;

import com.progresssoft.entity.FxDealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FxDealsRepository extends JpaRepository<FxDealEntity, Long> {

}