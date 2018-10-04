package com.pleshkov.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
     * Дата чека
     */
    private Date date;

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
    }
}
