package com.pleshkov.beans.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Структура XML для списка чеков
 * @author pleshkov on 03.10.2018.
 */
@XmlRootElement(name = "SALES")
public class XMLSales {

    /**
     * Список чеков
     */
    @XmlElement(name = "SALE")
    private List<XMLSale> sale;

    public List<XMLSale> getSales() {
        return sale;
    }

    public void setSales(List<XMLSale> sales) {
        this.sale = sales;
    }
}
