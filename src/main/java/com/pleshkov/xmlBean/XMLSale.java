package com.pleshkov.xmlBean;


/**
 * Структура XML для чека
 * @author pleshkov on 03.10.2018.
 */
public class XMLSale {
    /**
     * Номер карты
     */
    private Long CARD_NUMBER;

    /**
     * Дата
     */
    private Long DATE;

    /**
     * Список продуктов
     */
    private XMLProducts PRODUCTS;

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

    public XMLProducts getPRODUCTS() {
        return PRODUCTS;
    }

    public void setPRODUCTS(XMLProducts PRODUCTS) {
        this.PRODUCTS = PRODUCTS;
    }
}
