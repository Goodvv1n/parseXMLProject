package com.pleshkov.xmlBean;


/**
 * @author pleshkov on 03.10.2018.
 */
public class Sale {
    private int CARD_NUMBER;

    private Long DATE;

    private Products PRODUCTS;

    public Integer getCARD_NUMBER() {
        return CARD_NUMBER;
    }

    public void setCARD_NUMBER(Integer CARD_NUMBER) {
        this.CARD_NUMBER = CARD_NUMBER;
    }

    public Long getDATE() {
        return DATE;
    }

    public void setDATE(Long DATE) {
        this.DATE = DATE;
    }

    public Products getPRODUCTS() {
        return PRODUCTS;
    }

    public void setPRODUCTS(Products PRODUCTS) {
        this.PRODUCTS = PRODUCTS;
    }
}
