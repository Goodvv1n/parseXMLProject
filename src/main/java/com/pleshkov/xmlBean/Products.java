package com.pleshkov.xmlBean;

import java.util.List;

/**
 * @author pleshkov on 03.10.2018.
 */
public class Products {

    private List<Product> PRODUCT;

    public List<Product> getPRODUCT() {
        return PRODUCT;
    }

    public void setPRODUCT(List<Product> products) {
        this.PRODUCT = products;
    }
}
