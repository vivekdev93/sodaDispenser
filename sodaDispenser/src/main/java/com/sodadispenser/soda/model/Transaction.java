package com.sodadispenser.soda.model;

import java.util.Date;

public class Transaction {

    int id;
    double pricePaid;
    String type;
    String dateOfTransaction;


    public Transaction(String type, double pricePaid, String dateOfTransaction) {
        this.pricePaid = pricePaid;
        this.type = type;
        this.dateOfTransaction = dateOfTransaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(String dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }
}
