package com.pleshkov.data.repozitory;

import com.pleshkov.data.entity.SaleEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author pleshkov on 04.10.2018.
 */
@Repository
public interface SaleEventRepozitory extends CrudRepository<SaleEvent, Long> {
    List<SaleEvent> findAllByCardId(Long cardId);
    List<SaleEvent> findAllByDate(Date date);
    List<SaleEvent> findAllByCardIdAndDate(Long cardId, Date date);
}
