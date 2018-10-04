package com.pleshkov.data.repozitory;

import com.pleshkov.data.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pleshkov on 04.10.2018.
 */
@Repository
public interface ProductRepozitory extends CrudRepository<Product, Long> {
    List<Product> findByCode(Long code);
}
