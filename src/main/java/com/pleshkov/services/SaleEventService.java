package com.pleshkov.services;

import com.pleshkov.data.entity.SaleEvent;
import com.pleshkov.data.entity.SaleSum;
import com.pleshkov.data.repozitory.SaleEventRepozitory;
import com.pleshkov.data.repozitory.SaleSumRepository;
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

    @Autowired
    SaleSumRepository saleSumRepository;

    /**
     * Сохранить запись о чеке
     * @param saleEvent запись о чеке
     * @return результат
     */
    public SaleEvent save(SaleEvent saleEvent){
        return repozitory.save(saleEvent);
    }

    /**
     * Получить список чеков
     * @return результат
     */
    public List<SaleEvent> getSaleEventList(){
        return (List<SaleEvent>) repozitory.findAll();
    }

    /**
     * Получить список чеков по Ид карты
     * @param cardId Ид карты
     * @return результат
     */
    public List<SaleEvent> getSaleEventList(Long cardId){
        return repozitory.findAllByCardId(cardId);
    }

    /**
     * Получить список чеков по дате
     * @param date дата
     * @return результат
     */
    public List<SaleEvent> getSaleEventList(Date date){
        return repozitory.findAllByDate(date);
    }

    /**
     * получить список чеков по ИД карты и дате
     * @param cardId Ид карты
     * @param date дата
     * @return результат
     */
    public List<SaleEvent> getSaleEventList(Long cardId, Date date){
        return repozitory.findAllByCardIdAndDate(cardId, date);
    }

    /**
     * Сохранить сумму по чеку
     * @param saleSum сумма
     */
    public void saveSaleSum(SaleSum saleSum){
        saleSumRepository.save(saleSum);
    }

    /**
     * получить сумму по ИД чека
     * @param saleId Ид чека
     * @return результат
     */
    public Double getSaleSum(Long saleId){
        List<SaleSum> list = saleSumRepository.findBySaleId(saleId);
        if (list != null && list.size() > 0){
            return list.get(0).getSum();
        }
        return null;
    }

    /**
     * Удалить сумму по чеку
     * @param saleId Ид чека
     */
    public void deleteSaleSum(Long saleId){
        for (SaleSum sum : saleSumRepository.findBySaleId(saleId)){
            saleSumRepository.delete(sum);
        }
    }

    /**
     * Удалить запись о чеке
     * @param saleEvent запись о чеке
     */
    public void delete(SaleEvent saleEvent){
        repozitory.delete(saleEvent);
    }

}
