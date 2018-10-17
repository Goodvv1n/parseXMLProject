package com.pleshkov.data.repozitory;

import com.pleshkov.data.entity.SaleSum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pleshkov on 17.10.2018.
 */
@Repository
public interface SaleSumRepository extends CrudRepository<SaleSum, Long> {
    List<SaleSum> findBySaleId(Long saleId);
}
