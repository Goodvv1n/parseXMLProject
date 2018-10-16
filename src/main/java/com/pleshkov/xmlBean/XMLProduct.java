package com.pleshkov.xmlBean;

/**
 * @author pleshkov on 03.10.2018.
 */
public class XMLProduct {
    private Long PRODUCT_CODE;
    private String NAME;
    private double PRICE;
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
