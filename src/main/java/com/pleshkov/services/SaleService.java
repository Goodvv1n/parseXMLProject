package com.pleshkov.services;

import com.pleshkov.data.entity.Product;
import com.pleshkov.data.entity.ProductSale;
import com.pleshkov.data.entity.SaleEvent;
import com.pleshkov.data.entity.SaleSum;
import com.pleshkov.beans.view.TopProduct;
import com.pleshkov.beans.view.ViewProductSale;
import com.pleshkov.beans.xml.XMLProduct;
import com.pleshkov.beans.xml.XMLSale;
import com.pleshkov.beans.xml.XMLSales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author pleshkov on 04.10.2018.
 */
@Service
public class SaleService {

    @Autowired
    ProductService productService;

    @Autowired
    SaleEventService saleEventService;

    @Autowired
    ProductSaleService productSaleService;

    /**
     * Мап для хранения списка продуктов
     */
    private HashMap<Long, Product> productMap = new HashMap<>();

    /**
     * Сохранить чеки из XML
     * @param sales чеки из XML
     */
    public void save(XMLSales sales){
        for (XMLSale sale : sales.getSales()){
            SaleEvent saleEvent = new SaleEvent();
            saleEvent.setCardId(sale.getCARD_NUMBER());
            saleEvent.setDate(new Date(sale.getDATE()));
            saleEvent = saleEventService.save(saleEvent);
            Double sum = 0d;
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
                sum += product.getCOUNT() * product.getFormatPrice();
            }
            saleEvent.setSum(sum);
            saveSaleSum(saleEvent,  sum);
        }
    }

    /**
     * Получить список проданных продуктов
     * @return результат
     */
    public List<ProductSale> getProductSaleList(){
        return productSaleService.getProductSaleList();
    }

    /**
     * Получить сумму чеков за день
     * @param date дата
     * @return результат
     */
    public String getSumOnDay(Date date){
        Double sum = 0d;
        if ( date != null){
            sum = saleEventService.getSaleSum(date);
        }
        return String.format("%.2f", sum);
    }

    /**
     * Удалить данные о чеке
     * @param viewProductSale данные
     */
    public void deleteSale(ViewProductSale viewProductSale){
        List<SaleEvent> saleEventList = saleEventService.getSaleEventList(viewProductSale.getSaleEventId());
        for (SaleEvent event : saleEventList){
            saleEventService.delete(event);
        }
        List<ProductSale> productSales = productSaleService.findBySaleId(viewProductSale.getSaleEventId());
        for (ProductSale productSale : productSales){
            productSaleService.delete(productSale);
        }
        saleEventService.deleteSaleSum(viewProductSale.getSaleEventId());
    }

    /**
     * Получить список чеков
     * @return результат
     */
    public List<SaleEvent> getSaleEventList(){
        return saleEventService.getSaleEventList();
    }

    /**
     * Получить расширенный список проданных продуктов
     * @param cardId Ид карты
     * @param date дата
     * @return результат
     */
    public List<ViewProductSale> getViewSaleList(Long cardId, Date date){
        List<ViewProductSale> list = new ArrayList<>();
        List<SaleEvent> saleEventList;
        if (cardId == null && date == null){
            saleEventList = saleEventService.getSaleEventList();
        } else if (cardId != null && date == null){
            saleEventList = saleEventService.getSaleEventList(cardId);
        } else if (cardId == null){
            saleEventList = saleEventService.getSaleEventList(date);
        } else {
            saleEventList = saleEventService.getSaleEventList(cardId, date);
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

    /**
     * Получить Топ-3 продукта
     * @param cardId Ид карты
     * @return рещультат
     */
    public List<TopProduct> getTopList(Long cardId){
        List<ViewProductSale> productSaleList = getViewSaleList(cardId, null);
        Map<Long, ProductCounter> topProductMap = new HashMap<>();
        List<TopProduct> topList = new ArrayList<>();
        for (ViewProductSale item : productSaleList){
            ProductCounter counter = topProductMap.get(item.getProductId());
            if (counter == null) {
                counter = new ProductCounter(item);
                counter.setCount(item.getCount());
            } else {
                counter.addCount(item.getCount());
            }
            topProductMap.put(item.getProductId(), counter);
        }
        TreeSet<ProductCounter> set = new TreeSet<>();
        for (Map.Entry<Long, ProductCounter> item: topProductMap.entrySet()){
            set.add(item.getValue());
        }
        for (ProductCounter item : set){
            topList.add(item.getTopProduct());
            if (topList.size() == 3){
                break;
            }
        }
        return topList;
    }

    /**
     * Получить сумму чеков в списке купленных продуктов
     * @param productList список продуктов
     * @return результат
     */
    public String resultSum(List<ViewProductSale> productList){
        Double sum = 0d;
        for (ViewProductSale item : productList){
            sum += item.getPrice() * item.getCount();
        }
        return String.format("%.2f", sum);
    }

    /**
     * Получить число чеков по списку купленных продуктов
     * @param productList список
     * @return результат
     */
    public int saleCount(List<ViewProductSale> productList){
        HashMap<Long, Date> saleCountMap = new HashMap<>();
        for (ViewProductSale item : productList){
            saleCountMap.put(item.getSaleEventId(), item.getDate());
        }
        return saleCountMap.size();
    }

    /**
     * Получить полный список чеков в БД
     * @return резульат
     */
    public int absoluteSaleCount(){
        List<SaleEvent> list = saleEventService.getSaleEventList();
        return list.size();
    }

    /**
     * Вспомогательный класс для определения ТОП-3 продуктов
     */
    private class ProductCounter implements Comparable<ProductCounter>{
        /**
         * Ид продукта
         */
        private Long id;

        /**
         * Информация о продукте
         */
        private ViewProductSale sale;

        /**
         * Количество
         */
        private Integer count;

        ProductCounter(ViewProductSale productSale) {
            this.id = productSale.getProductId();
            this.sale = productSale;
        }

        public Long getId() {
            return id;
        }

        Integer getCount() {
            return count;
        }

        void setCount(Integer count) {
            this.count = count;
        }

        void addCount(Integer count){
            this.count += count;
        }

        ViewProductSale getSale() {
            return sale;
        }

        /**
         * Получить структуру для отображения TOП -продукта
         * @return результат
         */
        TopProduct getTopProduct(){
            TopProduct topProduct = new TopProduct();
            topProduct.setProductId(sale.getProductId());
            topProduct.setProductCode(sale.getProductCode());
            topProduct.setCount(count);
            topProduct.setProductName(sale.getProductName());
            return topProduct;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ProductCounter that = (ProductCounter) o;

            return count != null ? count.equals(that.count) : that.count == null;
        }

        @Override
        public int hashCode() {
            return 31 + (count != null ? count.hashCode() : 0);
        }

        @Override
        public int compareTo(ProductCounter o) {
            return o.getCount().compareTo(count);
        }
    }

    /**
     * Сохранить сумму по чеку
     * @param saleEvent Чек
     * @param sum сумма чека
     */
    private void saveSaleSum(SaleEvent saleEvent, Double sum){
        SaleSum saleSum = new SaleSum();
        saleSum.setSaleId(saleEvent.getId());
        saleSum.setDate(saleEvent.getDate());
        saleSum.setSum(sum);
        saleEventService.saveSaleSum(saleSum);
    }

    /**
     * Дполнить данные информацией о продукте
     * @param viewProductSale данные
     * @param productId Ид продукта
     * @return расширенные данные
     */
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
