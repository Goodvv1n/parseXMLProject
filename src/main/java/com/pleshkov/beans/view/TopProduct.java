package com.pleshkov.beans.view;

/**
 * Бин для отображения ТОП продукта
 * @author pleshkov on 15.10.2018.
 */
public class TopProduct {

    /**
     * Ид продукта
     */
    private Long productId;

    /**
     * Код продукта
     */
    private Long productCode;

    /**
     * Наименование продукта
     */
    private String productName;

    /**
     * Количество
     */
    private Integer count;

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
}
