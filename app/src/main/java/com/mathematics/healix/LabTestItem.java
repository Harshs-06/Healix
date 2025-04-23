package com.mathematics.healix;

public class LabTestItem {
    private String title, description, gender, ageGroup, testTime;
    private String actualPrice, totalPrice, discount;

    public LabTestItem(String title, String description, String gender, String ageGroup, String testTime,
                       String actualPrice, String totalPrice, String discount) {
        this.title = title;
        this.description = description;
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.testTime = testTime;
        this.actualPrice = actualPrice;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getGender() { return gender; }
    public String getAgeGroup() { return ageGroup; }
    public String getTestTime() { return testTime; }
    public String getActualPrice() { return actualPrice; }
    public String getTotalPrice() { return totalPrice; }
    public String getDiscount() { return discount; }
}

