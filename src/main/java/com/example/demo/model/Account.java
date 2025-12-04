package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "account")
public class Account {
    @Id
    @Column
    private int accountNo;

    @Column
    private String name;

    @Column
    private int balance;

    public Account() {

    }


    public Account(String name, int amount, int accNo) {
        this.name = name;
        this.balance = amount;
        this.accountNo = accNo;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
