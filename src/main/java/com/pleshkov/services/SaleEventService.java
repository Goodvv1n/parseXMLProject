package com.pleshkov.services;

import com.pleshkov.data.entity.SaleEvent;
import com.pleshkov.data.repozitory.SaleEventRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author pleshkov on 15.10.2018.
 */
@Service
public class SaleEventService {

    @Autowired
    SaleEventRepozitory repozitory;

    public SaleEvent save(SaleEvent saleEvent){
        return repozitory.save(saleEvent);
    }

    public List<SaleEvent> getSaleEventList(){
        return (List<SaleEvent>) repozitory.findAll();
    }

    public List<SaleEvent> getSaleEventList(Long cardId){
        return repozitory.findAllByCardId(cardId);
    }

    public List<SaleEvent> getSaleEventList(Date date){
        return repozitory.findAllByDate(date);
    }

    public List<SaleEvent> getSaleEventList(Long cardId, Date date){
        return repozitory.findAllByCardIdAndDate(cardId, date);
    }

    public void delete(SaleEvent saleEvent){
        repozitory.delete(saleEvent);
    }

}
