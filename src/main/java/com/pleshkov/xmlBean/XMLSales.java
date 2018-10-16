package com.pleshkov.xmlBean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author pleshkov on 03.10.2018.
 */
@XmlRootElement(name = "SALES")
public class XMLSales {

    @XmlElement(name = "SALE")
    private List<XMLSale> sale;

    public List<XMLSale> getSales() {
        return sale;
    }

    public void setSales(List<XMLSale> sales) {
        this.sale = sales;
    }
}
