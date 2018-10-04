package com.pleshkov.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Сущность проданного продукта
 * @author pleshkov on 04.10.2018.
 */
@Entity
public class ProductSale {

    /**
     * Ид продажи продукта
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Ид чека
     */
    private Long saleId;

    /**
     * Ид продукта
     */
    private Long productId;

    /**
     * Стоимость проданного продукта
     */
    private Double price;

    /**
     * Количество едениц проданного продукта
     */
    private int count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
