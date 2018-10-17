package com.pleshkov.xmlBean;

/**
 * Структура продукта в XML
 * @author pleshkov on 03.10.2018.
 */
public class XMLProduct {
    /**
     * Код продукта
     */
    private Long PRODUCT_CODE;

    /**
     * Наименование
     */
    private String NAME;

    /**
     * Стоимость продукта
     */
    private double PRICE;

    /**
     * Количество
     */
    private int COUNT;

    public Long getPRODUCT_CODE() {
        return PRODUCT_CODE;
    }

    public void setPRODUCT_CODE(Long PRODUCT_CODE) {
        this.PRODUCT_CODE = PRODUCT_CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPRICE() {
        return String.format("%.2f", PRICE);
    }

    public Double getFormatPrice() {
        return Double.valueOf(getPRICE().replace(",", "."));
    }

    public void setPRICE(String PRICE) {
        this.PRICE = new Double(PRICE.replace(",", "."));
    }

    public int getCOUNT() {
        return COUNT;
    }

    public void setCOUNT(int COUNT) {
        this.COUNT = COUNT;
    }
}
