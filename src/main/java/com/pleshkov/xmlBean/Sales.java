package com.pleshkov.xmlBean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author pleshkov on 03.10.2018.
 */
@XmlRootElement(name = "SALES")
public class Sales {

    @XmlElement(name = "SALE")
    private List<Sale> sale;

    public List<Sale> getSales() {
        return sale;
    }

    public void setSales(List<Sale> sales) {
        this.sale = sales;
    }
}
