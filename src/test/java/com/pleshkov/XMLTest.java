package com.pleshkov;

import com.pleshkov.util.XMLParser;
import com.pleshkov.beans.xml.XMLSales;
import com.pleshkov.beans.xml.XMLSale;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author pleshkov on 04.10.2018.
 */
@RunWith(JUnit4.class)
public class XMLTest {

    @Test
    public void parseXMLFileTest() throws JAXBException, FileNotFoundException {
        File file = ResourceUtils.getFile(this.getClass().getResource("/test.xml"));
        XMLSales sales = (XMLSales) XMLParser.parseFile(file, XMLSales.class);
        Assert.assertNotNull(sales);
        Assert.assertEquals(sales.getSales().size(), 5);
        XMLSale saleFirst = sales.getSales().get(0);
        XMLSale saleSecond = sales.getSales().get(1);
        checkSale(saleFirst, 78483L, 1528205653605L);
        checkSale(saleSecond, 7777L, 1528205653605L);
    }

    private void checkSale(XMLSale sale, Long cardNumber, Long date) {
        Assert.assertNotNull(sale);
        Assert.assertEquals(sale.getCARD_NUMBER(), cardNumber);
        Assert.assertEquals(sale.getDATE(), date);
        Assert.assertNotNull(sale.getPRODUCTS());
    }
}
