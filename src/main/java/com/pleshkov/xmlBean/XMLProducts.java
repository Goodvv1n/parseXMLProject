package com.pleshkov.xmlBean;

import java.util.List;

/**
 * Структура XML для списка продуктов
 * @author pleshkov on 03.10.2018.
 */
public class XMLProducts {

    /**
     * Список продуктов
     */
    private List<XMLProduct> PRODUCT;

    public List<XMLProduct> getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(List<XMLProduct> products) {
        this.PRODUCT = products;
    }
}
