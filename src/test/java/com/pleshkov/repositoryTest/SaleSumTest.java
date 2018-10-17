package com.pleshkov.repositoryTest;

import com.pleshkov.data.entity.SaleSum;
import com.pleshkov.services.SaleEventService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author pleshkov on 17.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleSumTest {

    @Autowired
    SaleEventService saleEventService;

    @Test
    public void addTest(){
        Double sum = saleEventService.getSaleSum(111L);
        Assert.assertNull(sum);

        SaleSum saleSum = new SaleSum();
        saleSum.setSaleId(111L);
        saleSum.setSum(Double.valueOf("123.45"));

        saleEventService.saveSaleSum(saleSum);

        sum = saleEventService.getSaleSum(111L);
        Assert.assertNotNull(sum);
        Assert.assertEquals(Double.valueOf("123.45"), sum);
    }

    @Test
    public void deleteTest(){
        SaleSum saleSum = new SaleSum();
        saleSum.setSaleId(111L);
        saleSum.setSum(Double.valueOf("123.45"));
        saleEventService.saveSaleSum(saleSum);

        Double sum = saleEventService.getSaleSum(111L);
        Assert.assertNotNull(sum);

        saleEventService.deleteSaleSum(111L);

        sum = saleEventService.getSaleSum(111L);
        Assert.assertNull(sum);
    }
}
