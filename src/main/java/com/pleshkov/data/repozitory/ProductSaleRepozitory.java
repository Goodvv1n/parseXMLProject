package com.pleshkov.data.repozitory;

import com.pleshkov.data.entity.ProductSale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pleshkov on 04.10.2018.
 */
@Repository
public interface ProductSaleRepozitory extends CrudRepository<ProductSale, Long> {
    List<ProductSale> findBySaleId(Long saleId);
    List<ProductSale> findByProductId(Long productId);
}
