package com.mathematics.healix;

public class HealthCheckupItem {
    private String title, description, testCount, tat, actualPrice, totalPrice, discount;
    private boolean isFavorite;

    public HealthCheckupItem(String title, String description, String testCount, String tat,
                             String actualPrice, String totalPrice, String discount) {
        this.title = title;
        this.description = description;
        this.testCount = testCount;
        this.tat = tat;
        this.actualPrice = actualPrice;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.isFavorite = isFavorite;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getTestCount() { return testCount; }
    public String getTat() { return tat; }
    public String getActualPrice() { return actualPrice; }
    public String getTotalPrice() { return totalPrice; }
    public String getDiscount() { return discount; }

}

