package com.pleshkov.viewBean;

/**
 * @author pleshkov on 15.10.2018.
 */
public class SaleParams {
    private Long cardId;
    private String date;

    public Long getCardId() {
        return cardId != null ? cardId : 0;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
