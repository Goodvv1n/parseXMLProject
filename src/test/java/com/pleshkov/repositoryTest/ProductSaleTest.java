package com.pleshkov.repositoryTest;

import com.pleshkov.data.entity.ProductSale;
import com.pleshkov.services.ProductSaleService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author pleshkov on 15.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductSaleTest {

    @Autowired
    private ProductSaleService service;

    @After
    public void clear(){
        for (ProductSale item : service.getProductSaleList()){
            service.delete(item);
        }
    }

    @Test
    public void findBySaleIdTest(){
        List<ProductSale> list = service.getProductSaleList();
        Assert.assertNotNull(list);
        int size = list.size();

        service.save(generate());
        list = service.getProductSaleList();
        Assert.assertNotNull(list);
        Assert.assertEquals(size + 1, list.size());

        List<ProductSale> listBySaleId = service.findBySaleId(159L);
        Assert.assertNotNull(listBySaleId);
        Assert.assertTrue(listBySaleId.size() > 0);
    }

    @Test
    public void findByProductIdTest(){
        List<ProductSale> list = service.getProductSaleList();
        Assert.assertNotNull(list);
        int size = list.size();

        ProductSale productSale = generate();
        productSale.setProductId(111L);
        productSale.setSaleId(222L);
        service.save(productSale);
        service.save(generate());
        list = service.getProductSaleList();
        Assert.assertNotNull(list);
        Assert.assertEquals(size + 2, list.size());

        List<ProductSale> byProductId = service.findByProductId(111L);
        Assert.assertNotNull(byProductId);
        Assert.assertEquals(byProductId.size(), 1);
    }


    @Test
    public void addTest(){
        List<ProductSale> list = service.getProductSaleList();
        Assert.assertNotNull(list);
        int size = list.size();

        service.save(generate());

        list = service.getProductSaleList();
        Assert.assertNotNull(list);
        Assert.assertEquals(size + 1, list.size());
    }

    private ProductSale generate(){
        ProductSale productSale = new ProductSale();
        productSale.setProductId(456L);
        productSale.setPrice(1.25);
        productSale.setCount(10);
        productSale.setSaleId(159L);
        return productSale;
    }
}
