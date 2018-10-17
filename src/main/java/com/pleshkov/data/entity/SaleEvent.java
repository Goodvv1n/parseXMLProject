package com.pleshkov.data.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность "Чека".
 * @author pleshkov on 04.10.2018.
 */
@Entity
public class SaleEvent {

    /**
     * Ид чека
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Номер карты клиента
     */
    private Long cardId;

    /**
     * Сумма чека
     */
    private Double sum;

    /**
     * Дата чека
     */
    @Temporal(TemporalType.DATE)
    private Date date;

    /**
     * Время чека
     */
    @Temporal(TemporalType.TIME)
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        this.time = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.date = date;
        this.time = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
