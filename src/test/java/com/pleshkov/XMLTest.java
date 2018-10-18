package com.pleshkov;

import com.pleshkov.beans.xml.XMLProduct;
import com.pleshkov.beans.xml.XMLProducts;
import com.pleshkov.beans.xml.XMLSale;
import com.pleshkov.beans.xml.XMLSales;
import com.pleshkov.util.XMLParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.ResourceUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author pleshkov on 04.10.2018.
 */
@RunWith(JUnit4.class)
public class XMLTest {

    @Test
    public void parseXMLFileTest() throws JAXBException, FileNotFoundException, ParseException {
        File file = ResourceUtils.getFile(this.getClass().getResource("/template.xml"));
        XMLSales sales = (XMLSales) XMLParser.parseFile(file, XMLSales.class);
        Assert.assertNotNull(sales);
        Assert.assertEquals(sales.getSales().size(), 10);
        checkSale(sales);
    }

    private void checkSale(XMLSales sale) throws ParseException {
        Assert.assertNotNull(sale);
        List<XMLSale> saleList = sale.getSales();
        Assert.assertNotNull(saleList);

        int size = saleList.size();
        Assert.assertTrue(size > 0);

        for (int i = 1; i < size + 1; i++ ){
            checkSaleItem(saleList.get(i - 1), i);
        }
    }

    private void checkProduct(XMLProduct product, int version){
        Assert.assertEquals(Long.valueOf(version), product.getPRODUCT_CODE());
        Assert.assertEquals("ProductName" + version, product.getNAME());
        Assert.assertEquals(31 * version, product.getCOUNT());
        Assert.assertEquals(Double.valueOf(1.27 * version), product.getFormatPrice());
        Assert.assertEquals(String.format("%.2f", 1.27d * version), product.getPRICE());
    }

    private void checkSaleItem(XMLSale sale, int version) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Assert.assertNotNull(sale);
        Assert.assertEquals(Long.valueOf(11 * version), sale.getCARD_NUMBER());
        Date date = sdf.parse(String.format("0%s.0%s.200%s", version, version, version));
        Assert.assertEquals(Long.valueOf(date.getTime()), sale.getDATE());

        XMLProducts products = sale.getPRODUCTS();
        Assert.assertNotNull(products);
        List<XMLProduct> productList = products.getPRODUCT();
        Assert.assertNotNull(productList);
        Assert.assertEquals(productList.size(), version);
        for (int i = 1; i < version + 1; i++){
            checkProduct(productList.get(i - 1), i);
        }
    }

    /**
     * Метод для генерации шаблонного файла с чеками
     * @param count кол-во чеков
     * @return результат
     */
    private XMLSales generateSaleList(int count) throws ParseException {
        XMLSales result = new XMLSales();
        List<XMLSale> saleList = new ArrayList<>();
        for (int i = 1; i < count + 1; i++){
            saleList.add(generateSale(i));
        }
        result.setSales(saleList);
        return result;
    }

    private XMLSale generateSale(int version) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        XMLSale sale = new XMLSale();
        sale.setCARD_NUMBER(11L * version);
        sale.setPRODUCTS(generateProductList(version));
        sale.setDATE(sdf.parse(String.format("0%s.0%s.200%s", version,version,version)).getTime());
        return sale;
    }

    private XMLProducts generateProductList(int version){
        XMLProducts products = new XMLProducts();
        ArrayList<XMLProduct> productList = new ArrayList<>();
        for (int i = 1; i < version + 1; i++){
            productList.add(generateProduct(i));
        }
        products.setPRODUCT(productList);
        return products;
    }

    private XMLProduct generateProduct(int version){
        XMLProduct product = new XMLProduct();
        product.setPRODUCT_CODE((long) version);
        product.setNAME("ProductName" + version);
        product.setCOUNT(31* version);
        product.setPRICE(String.valueOf(1.27 * version));
        return product;
    }
}
