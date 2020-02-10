package com.hzm.concurrent.chapter11;

/**
 * 模拟数据
 */
public class Context {
    private String name;
    private String cardId;
    public void setName(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setCardId(String cardId) {
        this.cardId=cardId;
    }

    public String getCardId() {
        return cardId;
    }
}
