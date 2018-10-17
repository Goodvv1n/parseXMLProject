package com.pleshkov.services;

import com.pleshkov.data.entity.ProductSale;
import com.pleshkov.data.repozitory.ProductSaleRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pleshkov on 15.10.2018.
 */
@Service
public class ProductSaleService {

    @Autowired
    ProductSaleRepozitory repozitory;

    /**
     * Сохранить информацию о купленном товаре
     * @param productSale инфо
     */
    public void save(ProductSale productSale){
        repozitory.save(productSale);
    }

    /**
     * Получить список купленных товаров
     * @return результат
     */
    public List<ProductSale> getProductSaleList(){
        return (List<ProductSale>) repozitory.findAll();
    }

    /**
     * Получить список купленный товаров по Ид чека
     * @param saleId Ид чека
     * @return результат
     */
    public List<ProductSale> findBySaleId(Long saleId){
        return repozitory.findBySaleId(saleId);
    }

    /**
     * Получить список купленных товаров по Ид продукта
     * @param productId Ид продукта
     * @return результат
     */
    public List<ProductSale> findByProductId(Long productId){
       return repozitory.findByProductId(productId);
    }

    /**
     * Удалить информацию о купленном товаре
     * @param productSale инфо
     */
    public void delete(ProductSale productSale){
        repozitory.delete(productSale);
    }
}
