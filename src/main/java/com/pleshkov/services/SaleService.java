package com.pleshkov.services;

import com.pleshkov.data.entity.Product;
import com.pleshkov.data.entity.ProductSale;
import com.pleshkov.data.entity.SaleEvent;
import com.pleshkov.viewBean.ViewProductSale;
import com.pleshkov.xmlBean.XMLProduct;
import com.pleshkov.xmlBean.XMLSale;
import com.pleshkov.xmlBean.XMLSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author pleshkov on 04.10.2018.
 */
@Service
public class SaleService {

    @Autowired
    ProductService productService;

    @Autowired
    SaleEventService saleService;

    @Autowired
    ProductSaleService productSaleService;

    private HashMap<Long, Product> productMap = new HashMap<>();

    public void save(XMLSales sales){
        for (XMLSale sale : sales.getSales()){
            SaleEvent saleEvent = new SaleEvent();
            saleEvent.setCardId(sale.getCARD_NUMBER());
            saleEvent.setDate(new Date(sale.getDATE()));
            saleEvent = saleService.save(saleEvent);

            for (XMLProduct product : sale.getPRODUCTS().getPRODUCT()){
                Product existProduct = productService.findByCode(product.getPRODUCT_CODE());
                if (existProduct == null){
                    existProduct = productService.saveProduct(product);
                }
                ProductSale productSale = new ProductSale();
                productSale.setProductId(existProduct.getId());
                productSale.setSaleId(saleEvent.getId());
                productSale.setCount(product.getCOUNT());
                productSale.setPrice(product.getFormatPrice());
                productSaleService.save(productSale);
            }
        }
    }

    public List<ProductSale> getProductSaleList(){
        return productSaleService.getProductSaleList();
    }

    public List<SaleEvent> getSaleEventList(){
        return saleService.getSaleEventList();
    }

    public List<ViewProductSale> getViewSaleList(Long cardId, Date date){
        List<ViewProductSale> list = new ArrayList<>();
        List<SaleEvent> saleEventList;
        if (cardId == null && date == null){
            saleEventList = saleService.getSaleEventList();
        } else if (cardId != null && date == null){
            saleEventList = saleService.getSaleEventList(cardId);
        } else if (cardId == null){
            saleEventList = saleService.getSaleEventList(date);
        } else {
            saleEventList = saleService.getSaleEventList(cardId, date);
        }
        for (SaleEvent saleEvent : saleEventList){
            for (ProductSale productSale : productSaleService.findBySaleId(saleEvent.getId())){
                ViewProductSale viewProductSale = new ViewProductSale();
                viewProductSale.setSaleEventId(saleEvent.getId());
                viewProductSale.setProductId(productSale.getProductId());
                viewProductSale.setCardId(saleEvent.getCardId());
                viewProductSale.setPrice(productSale.getPrice());
                viewProductSale.setCount(productSale.getCount());
                viewProductSale.setDate(saleEvent.getDate());
                loadProductInfo(viewProductSale, productSale.getProductId());
                list.add(viewProductSale);
            }
        }
        return list;
    }

    private ViewProductSale loadProductInfo(ViewProductSale viewProductSale, Long productId){
        Product product = productMap.get(productId);
        if (product == null){
            product = productService.findById(productId);
            productMap.put(productId, product);
        }
        if (product != null){
            viewProductSale.setProductCode(product.getCode());
            viewProductSale.setProductName(product.getName());
        }
        return viewProductSale;
    }
}
