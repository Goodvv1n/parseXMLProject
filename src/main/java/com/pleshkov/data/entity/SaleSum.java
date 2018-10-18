package com.pleshkov.data.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность для хранения суммы по чекам
 * @author pleshkov on 17.10.2018.
 */
@Entity
public class SaleSum {
    /**
     * Ид записи
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Ид чека
     */
    private Long saleId;

    @Temporal(TemporalType.DATE)
    private Date date;


    /**
     * Сумма чека
     */
    private Double sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
