package com.pleshkov.xml.xmlBean;


/**
 * @author pleshkov on 03.10.2018.
 */
public class XMLSale {
    private Long CARD_NUMBER;

    private Long DATE;

    private Products PRODUCTS;

    public Long getCARD_NUMBER() {
        return CARD_NUMBER;
    }

    public void setCARD_NUMBER(Long CARD_NUMBER) {
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
