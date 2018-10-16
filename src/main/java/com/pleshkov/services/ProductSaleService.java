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

    public void save(ProductSale productSale){
        repozitory.save(productSale);
    }

    public List<ProductSale> getProductSaleList(){
        return (List<ProductSale>) repozitory.findAll();
    }

    public List<ProductSale> findBySaleId(Long saleId){
        return repozitory.findBySaleId(saleId);
    }

    public List<ProductSale> findByProductId(Long productId){
       return repozitory.findByProductId(productId);
    }

    public void delete(ProductSale productSale){
        repozitory.delete(productSale);
    }
}
