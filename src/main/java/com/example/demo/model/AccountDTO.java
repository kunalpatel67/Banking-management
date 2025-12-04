package com.example.demo.model;

public class AccountDTO {
    private String name;
    private String date;
    private int amount;
    private int fromAcc;
    private int toAcc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFromAcc() {
        return fromAcc;
    }

    public void setFromAcc(int fromAcc) {
        this.fromAcc = fromAcc;
    }

    public int getToAcc() {
        return toAcc;
    }

    public void setToAcc(int toAcc) {
        this.toAcc = toAcc;
    }
}
