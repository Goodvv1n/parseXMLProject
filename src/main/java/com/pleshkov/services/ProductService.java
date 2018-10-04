package com.pleshkov.services;

import com.pleshkov.data.entity.Product;
import com.pleshkov.data.repozitory.ProductRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pleshkov on 04.10.2018.
 */
@Service
public class ProductService {
    @Autowired
    ProductRepozitory productRepozitory;

    public void saveProduct(Product product){
        productRepozitory.save(product);
    }

    public List<Product> getProducts(){
        return (List<Product>) productRepozitory.findAll();
    }

    public void deleteProduct(Product product){
        productRepozitory.delete(product);
    }

    public Product findByCode(Long code){
        List<Product> list = productRepozitory.findByCode(code);
        if (list != null && list.size() != 0){
            return list.get(0);
        }
        return null;
    }
}
