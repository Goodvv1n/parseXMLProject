package com.pleshkov.beans.view;

import java.util.Date;

/**
 * Отображение
 * @author pleshkov on 15.10.2018.
 */
public class ViewProductSale {
    private Long productId;

    private Long saleEventId;

    private Long productCode;

    private String productName;

    private Integer count;

    private Double price;

    private Date date;

    private Long cardId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public String getFormatPrice() {
        return String.format("%.2f", price);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getSaleEventId() {
        return saleEventId;
    }

    public void setSaleEventId(Long saleEventId) {
        this.saleEventId = saleEventId;
    }
}
