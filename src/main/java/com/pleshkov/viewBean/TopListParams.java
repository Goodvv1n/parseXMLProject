package com.pleshkov.viewBean;

/**
 * @author pleshkov on 15.10.2018.
 */
public class TopListParams {
    private Long cardId;

    public Long getCardId() {
        return cardId != null ? cardId : 0;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
