package com.pleshkov.services;

import com.pleshkov.data.entity.Product;
import com.pleshkov.data.repozitory.ProductRepozitory;
import com.pleshkov.xmlBean.XMLProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author pleshkov on 04.10.2018.
 */
@Service
public class ProductService {
    @Autowired
    ProductRepozitory productRepozitory;

    HashMap<Long, Product> productMap = new HashMap<>();

    public void saveProduct(Product product){
        productRepozitory.save(product);
    }

    public Product saveProduct(XMLProduct product){
        Product trueProduct = new Product();
        trueProduct.setName(product.getNAME());
        trueProduct.setCode(product.getPRODUCT_CODE());
        return productRepozitory.save(trueProduct);
    }

    public List<Product> getProducts(){
        return (List<Product>) productRepozitory.findAll();
    }

    public void deleteProduct(Product product){
        productRepozitory.delete(product);
    }

    public Product findByCode(Long code){
        Product product = productMap.get(code);
        if (product == null) {
            List<Product> list = productRepozitory.findByCode(code);
            if (list != null && list.size() != 0) {
                product = list.get(0);
                productMap.put(code, product);
                return product;
            }
        }
        return null;
    }

    public Product findById(Long productId){
        Optional<Product> product = productRepozitory.findById(productId);
        return product.isPresent() ? product.get() : null;
    }
}
