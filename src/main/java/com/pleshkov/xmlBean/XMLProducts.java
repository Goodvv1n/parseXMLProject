package com.pleshkov.xmlBean;

import java.util.List;

/**
 * @author pleshkov on 03.10.2018.
 */
public class XMLProducts {

    private List<XMLProduct> PRODUCT;

    public List<XMLProduct> getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(List<XMLProduct> products) {
        this.PRODUCT = products;
    }
}
