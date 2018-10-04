package com.pleshkov.data.repozitory;

import com.pleshkov.data.entity.SaleEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pleshkov on 04.10.2018.
 */
@Repository
public interface SaleRepozitory extends CrudRepository<SaleEvent, Long> {
}