package com.example.ordersysapp.Model;


public class Bill {
    private String billQuantity;
    private String billName;
    private String billPrice;

    public Bill(String billQuantity, String billName, String billPrice) {

        this.billQuantity = billQuantity;
        this.billName = billName;
        this.billPrice = billPrice;
    }

    public String getBillQuantity() {
        return billQuantity;
    }

    public void setBillQuantity(String billQuantity) {
        this.billQuantity = billQuantity;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(String billPrice) {
        this.billPrice = billPrice;
    }
}