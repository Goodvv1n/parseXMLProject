package com.pleshkov.repositoryTest;

import com.pleshkov.data.entity.SaleSum;
import com.pleshkov.services.SaleEventService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author pleshkov on 17.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleSumTest {

    private static final Long SALEID = 111L;

    @Autowired
    SaleEventService saleEventService;

    @After
    public void clear(){
        saleEventService.deleteSaleSum(SALEID);
    }

    @Test
    public void addTest(){
        Double sum = saleEventService.getSaleSum(SALEID );
        Assert.assertEquals(Double.valueOf(0), sum);

        SaleSum saleSum = new SaleSum();
        saleSum.setSaleId(SALEID );
        Date date = new Date(978296400000L);
        saleSum.setDate(date);
        saleSum.setSum(Double.valueOf("123.45"));

        saleEventService.saveSaleSum(saleSum);

        sum = saleEventService.getSaleSum(SALEID );
        Assert.assertNotNull(sum);
        Assert.assertEquals(Double.valueOf("123.45"), sum);
    }

    @Test
    public void findBySaleIdTest(){
        Double sum = saleEventService.getSaleSum(SALEID );
        Assert.assertEquals(Double.valueOf(0), sum);

        SaleSum saleSum = new SaleSum();
        saleSum.setSaleId(SALEID );
        Date date = new Date(978296400000L);
        saleSum.setDate(date);
        saleSum.setSum(Double.valueOf("123.45"));

        SaleSum saleSumSecond = new SaleSum();
        saleSumSecond.setSaleId(SALEID );
        Date dateSecond = new Date(1539848000000L);
        saleSumSecond.setDate(dateSecond);
        saleSumSecond.setSum(Double.valueOf("789.65"));

        saleEventService.saveSaleSum(saleSum);
        saleEventService.saveSaleSum(saleSumSecond);

        sum = saleEventService.getSaleSum(SALEID , dateSecond);
        Assert.assertNotNull(sum);
        Assert.assertEquals(Double.valueOf("789.65"), sum);

        sum = saleEventService.getSaleSum(SALEID , date);
        Assert.assertNotNull(sum);
        Assert.assertEquals(Double.valueOf("123.45"), sum);
    }

    @Test
    public void findByDateTest(){
        Double sum = saleEventService.getSaleSum(SALEID);
        Assert.assertEquals(Double.valueOf(0), sum);

        SaleSum saleSum = new SaleSum();
        saleSum.setSaleId(SALEID );
        Date date = new Date(978296400000L);
        saleSum.setDate(date);
        saleSum.setSum(Double.valueOf("123.45"));

        SaleSum saleSumThird = new SaleSum();
        saleSumThird.setSaleId(SALEID );
        Date dateThird = new Date(978296400000L);
        saleSumThird.setDate(dateThird);
        saleSumThird.setSum(Double.valueOf("123.55"));

        SaleSum saleSumSecond = new SaleSum();
        saleSumSecond.setSaleId(SALEID );
        Date dateSecond = new Date(1539848000000L);
        saleSumSecond.setDate(dateSecond);
        saleSumSecond.setSum(Double.valueOf("789.65"));

        saleEventService.saveSaleSum(saleSum);
        saleEventService.saveSaleSum(saleSumSecond);
        saleEventService.saveSaleSum(saleSumThird);

        sum = saleEventService.getSaleSum(dateSecond);
        Assert.assertNotNull(sum);
        Assert.assertEquals(Double.valueOf("789.65"), sum);

        sum = saleEventService.getSaleSum(date);
        Assert.assertNotNull(sum);
        Assert.assertEquals(Double.valueOf("247.0"), sum);
    }

    @Test
    public void deleteTest(){
        SaleSum saleSum = new SaleSum();
        saleSum.setSaleId(SALEID );
        saleSum.setSum(Double.valueOf("123.45"));
        saleEventService.saveSaleSum(saleSum);

        Double sum = saleEventService.getSaleSum(SALEID );
        Assert.assertNotNull(sum);

        saleEventService.deleteSaleSum(SALEID );

        sum = saleEventService.getSaleSum(SALEID );
        Assert.assertEquals(Double.valueOf(0), sum);
    }
}
