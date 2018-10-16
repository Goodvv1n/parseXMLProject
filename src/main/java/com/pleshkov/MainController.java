package com.pleshkov;

import com.pleshkov.data.entity.LoadInfo;
import com.pleshkov.data.entity.SaleEvent;
import com.pleshkov.services.LoadInfoService;
import com.pleshkov.services.SaleService;
import com.pleshkov.viewBean.SaleParams;
import com.pleshkov.viewBean.TopListParams;
import com.pleshkov.viewBean.TopProduct;
import com.pleshkov.viewBean.ViewProductSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author pleshkov on 03.10.2018.
 */
@Controller
public class MainController {

    private static final String template = "Hello, %s!";

    @Autowired
    SaleService saleService;

    @Autowired
    LoadInfoService loadInfoService;

    @RequestMapping("/saleList")
    public String saleList(@ModelAttribute SaleParams saleParams, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Long cardId = null;
        try {
            if (saleParams != null
                    && saleParams.getDate() != null
                    && !saleParams.getDate().isEmpty()) {
                date = sdf.parse(saleParams.getDate());
            }
        } catch (ParseException e) {
            System.out.println("Ошибка при парсинге даты");
        }
        if (saleParams != null && saleParams.getCardId() != 0){
            cardId = saleParams.getCardId();
        }
        List<ViewProductSale> productSaleList;
        if (cardId != null){
            productSaleList = saleService.getViewSaleList(cardId, date);
        } else {
            productSaleList = new ArrayList<>();
        }
        model.addAttribute("productList", productSaleList);
        model.addAttribute("sum", resultSum(productSaleList));
        model.addAttribute("saleCount", saleCount(productSaleList));
        model.addAttribute("absSaleCount", absoluteSaleCount());
        return "saleList";
    }

    private String resultSum(List<ViewProductSale> productList){
        Double sum = 0d;
        for (ViewProductSale item : productList){
            sum += item.getPrice() * item.getCount();
        }
        return String.format("%.2f", sum);
    }

    @RequestMapping("/topList")
    public String topList(@ModelAttribute TopListParams topListParams, Model model) {
        Long cardId = null;
        if (topListParams != null && topListParams.getCardId() != 0){
            cardId = topListParams.getCardId();
        }
        List<TopProduct> topList = new ArrayList<>();
        if (cardId != null){
            List<ViewProductSale> productSaleList = saleService.getViewSaleList(cardId, null);
            topList = getTopList(productSaleList);
        }
        model.addAttribute("topList", topList);
        return "topList";
    }

    @RequestMapping("/loadedFiles")
    public String topList(Model model) {
       List<LoadInfo> fileList = loadInfoService.getAll();
        model.addAttribute("fileList", fileList);
        return "loadedFiles";
    }

    private List<TopProduct> getTopList(List<ViewProductSale> productSaleList){
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

    private int saleCount(List<ViewProductSale> productList){
        HashMap<Long, Date> saleCountMap = new HashMap<>();
        for (ViewProductSale item : productList){
            saleCountMap.put(item.getSaleEventId(), item.getDate());
        }
        return saleCountMap.size();
    }

    private int absoluteSaleCount(){
        List<SaleEvent> list = saleService.getSaleEventList();
        return list.size();
    }

    private class ProductCounter implements Comparable<ProductCounter>{
        private Long id;
        private ViewProductSale sale;

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
}
