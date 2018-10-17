package com.pleshkov.repositoryTest;

import com.pleshkov.data.entity.Product;
import com.pleshkov.data.entity.ProductSale;
import com.pleshkov.data.entity.SaleEvent;
import com.pleshkov.services.ProductSaleService;
import com.pleshkov.services.ProductService;
import com.pleshkov.services.SaleEventService;
import com.pleshkov.services.SaleService;
import com.pleshkov.util.XMLParser;
import com.pleshkov.viewBean.ViewProductSale;
import com.pleshkov.xmlBean.XMLSales;
import org.junit.After;
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
import java.util.List;

/**
 * @author pleshkov on 15.10.2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleTest {

    @Autowired
    SaleService saleService;

    @Autowired
    ProductService productService;

    @Autowired
    SaleEventService saleEventService;

    @Autowired
    ProductSaleService productSaleService;

    @After
    public void clear(){
        for (ViewProductSale viewProductSale : saleService.getViewSaleList(null, null)){
            saleService.deleteSale(viewProductSale);
        }
        for (SaleEvent event : saleEventService.getSaleEventList()){
            saleEventService.delete(event);
        }
    }

    @Test
    public void addTest() throws FileNotFoundException, JAXBException {
        XMLSales sales = generateSales();
        saleService.save(sales);

        List<Product> productList = productService.getProducts();
        Assert.assertNotNull(productList);
        Assert.assertEquals(10, productList.size());

        List<SaleEvent> saleEventList = saleEventService.getSaleEventList();
        Assert.assertNotNull(saleEventList);
        Assert.assertEquals(5, saleEventList.size());

        List<ProductSale> productSaleList = productSaleService.getProductSaleList();
        Assert.assertNotNull(productSaleList);
        Assert.assertEquals(36, productSaleList.size());

        int saleSize = saleEventService.getSaleEventList().size();
        int productSize = productService.getProducts().size();
        int productSaleSize = productSaleService.getProductSaleList().size();

        saleService.save(sales);

        productList = productService.getProducts();
        Assert.assertNotNull(productList);
        Assert.assertEquals(productSize, productList.size());

        saleEventList = saleEventService.getSaleEventList();
        Assert.assertNotNull(saleEventList);
        Assert.assertEquals(saleSize * 2, saleEventList.size());

        productSaleList = productSaleService.getProductSaleList();
        Assert.assertNotNull(productSaleList);
        Assert.assertEquals(productSaleSize * 2, productSaleList.size());
    }

    private XMLSales generateSales() throws FileNotFoundException, JAXBException {
        File file = ResourceUtils.getFile(this.getClass().getResource("/text.xml"));
        return (XMLSales) XMLParser.parseFile(file, XMLSales.class);
    }
}
