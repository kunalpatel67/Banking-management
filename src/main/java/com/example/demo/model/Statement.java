package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table (name = "statement")
public class Statement {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int accountNo;

    @Column
    private int amount;

    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @Column
    private String date;

    public Statement(){

    }

    public Statement(int accountNo, int amount, TypeEnum type, String date) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
