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

    /**
     * Мап для хранения списка продуктов
     */
    private HashMap<Long, Product> productMap = new HashMap<>();

    /**
     * Сохранить запись о продукте
     * @param product продукт
     */
    public void saveProduct(Product product){
        productRepozitory.save(product);
    }

    /**
     * Сохранить информацию о продукте из XML
     * @param product инфо о  продукте из XML
     * @return результат
     */
    Product saveProduct(XMLProduct product){
        Product trueProduct = new Product();
        trueProduct.setName(product.getNAME());
        trueProduct.setCode(product.getPRODUCT_CODE());
        return productRepozitory.save(trueProduct);
    }

    /**
     * Получить список продуктов
     * @return результат
     */
    public List<Product> getProducts(){
        return (List<Product>) productRepozitory.findAll();
    }

    /**
     * Удалить запись о продукте
     * @param product продукт
     */
    public void deleteProduct(Product product){
        if (productMap.get(product.getCode()) != null){
            productMap.remove(product.getCode());
        }
        productRepozitory.delete(product);
    }

    /**
     * Найти продукт по коду
     * Для более быстрого поиска храним найденные продукты в мапе.
     * @param code код продукта
     * @return результат
     */
    public Product findByCode(Long code){
        Product product = productMap.get(code);
        if (product == null) {
            List<Product> list = productRepozitory.findByCode(code);
            if (list != null && list.size() != 0) {
                product = list.get(0);
                productMap.put(code, product);
            }
        }
        return product;
    }

    /**
     * Найти продукт по ИД
     * @param productId Ид
     * @return результат
     */
    Product findById(Long productId){
        Optional<Product> product = productRepozitory.findById(productId);
        return product.isPresent() ? product.get() : null;
    }
}
