package com.pleshkov.serviceTest;

import com.pleshkov.services.SaleService;
import com.pleshkov.util.XMLParser;
import com.pleshkov.beans.view.TopProduct;
import com.pleshkov.beans.view.ViewProductSale;
import com.pleshkov.beans.xml.XMLSales;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

/**
 * @author pleshkov on 17.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {

    @Autowired
    SaleService saleService;

    @Test
    public void saleServiceTest() throws FileNotFoundException, JAXBException {
        File file = ResourceUtils.getFile("src/test/resourses/text.xml");
        XMLSales sales = (XMLSales) XMLParser.parseFile(file, XMLSales.class);
        saleService.save(sales);
        List<ViewProductSale> list = saleService.getViewSaleList(7777L, null);
        Assert.assertNotNull(list);
        Assert.assertEquals(22, list.size());

        List<TopProduct> topList = saleService.getTopList(7777L);
        Assert.assertNotNull(topList);
        Assert.assertEquals(3, topList.size());

        Assert.assertEquals(Long.valueOf(7) , topList.get(0).getProductCode());
        Assert.assertEquals(Long.valueOf(8) , topList.get(1).getProductCode());
        Assert.assertEquals(Long.valueOf(6) , topList.get(2).getProductCode());

        Assert.assertEquals(Integer.valueOf(2222) , topList.get(0).getCount());
        Assert.assertEquals(Integer.valueOf(15), topList.get(1).getCount());
        Assert.assertEquals(Integer.valueOf(14) , topList.get(2).getCount());

        Assert.assertEquals("65307,05", saleService.resultSum(list));
        Assert.assertEquals(5, saleService.absoluteSaleCount());

        Assert.assertEquals(String.valueOf("68316,94"), saleService.getSumOnDay(new Date(1528205653605L)));
    }

}
