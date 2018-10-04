package com.pleshkov.repositoryTest;

import com.pleshkov.data.entity.Product;
import com.pleshkov.services.ProductService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author pleshkov on 04.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {
    private static final Long PRODUCT_CODE = 123L;
    private static final String PRODUCT_NAME = "NAME";

    @Autowired
    private ProductService service;

    @After
    public void afterTest(){
        Product product = service.findByCode(PRODUCT_CODE);
        if (product != null){
            service.deleteProduct(product);
        }
    }

    @Test
    public void addProductTest(){
        List<Product> list = service.getProducts();
        Assert.assertNotNull(list);
        int size = list.size();

        generateProduct();
        list = service.getProducts();
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), size + 1);
    }

    @Test
    public void findByCodeTest(){
        Product product = service.findByCode(PRODUCT_CODE);
        Assert.assertNull(product);
        generateProduct();

        product = service.findByCode(PRODUCT_CODE);
        Assert.assertNotNull(product);
        Assert.assertEquals(product.getCode(), PRODUCT_CODE);
        Assert.assertEquals(product.getName(), PRODUCT_NAME);
        Assert.assertNotNull(product.getId());
    }

    @Test
    public void deleteProductTest(){
        generateProduct();
        List<Product> list = service.getProducts();
        int size = list.size();
        Product product = service.findByCode(PRODUCT_CODE);
        Assert.assertNotNull(product);
        service.deleteProduct(product);
        list = service.getProducts();
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), size - 1);
    }

    private void generateProduct(){
        Product product = new Product();
        product.setCode(PRODUCT_CODE);
        product.setName(PRODUCT_NAME);
        service.saveProduct(product);
    }
}
