package com.money.moneyworld.Model;

public class GameModel {
    Integer Number;
    String Amount;

    public GameModel(Integer number, String amount) {
        Number = number;
        Amount = amount;
    }

    public GameModel() {
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
