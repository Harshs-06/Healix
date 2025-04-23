package com.mathematics.healix;

public class PackageItem {
    String title, desc, noOfTests, tat, actualPrice, totalPrice, discount;

    public PackageItem(String title, String desc, String noOfTests, String tat, String actualPrice, String totalPrice, String discount) {
        this.title = title;
        this.desc = desc;
        this.noOfTests = noOfTests;
        this.tat = tat;
        this.actualPrice = actualPrice;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDesc() { return desc; }
    public String getNoOfTests() { return noOfTests; }
    public String getTat() { return tat; }
    public String getActualPrice() { return actualPrice; }
    public String getTotalPrice() { return totalPrice; }
    public String getDiscount() { return discount; }
}

