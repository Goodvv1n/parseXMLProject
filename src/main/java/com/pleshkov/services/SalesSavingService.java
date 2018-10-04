package com.pleshkov.services;

import com.pleshkov.data.entity.Product;
import com.pleshkov.data.repozitory.ProductRepozitory;
import com.pleshkov.data.repozitory.SaleRepozitory;
import com.pleshkov.xml.xmlBean.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pleshkov on 04.10.2018.
 */
@Service
public class SalesSavingService {

    @Autowired
    ProductRepozitory productRepozitory;

    @Autowired
    SaleRepozitory saleRepozitory;



    public void saveSales(Sales sales){

    }

    private void saveProduct(Product product){
        productRepozitory.delete(product);
    }
}
