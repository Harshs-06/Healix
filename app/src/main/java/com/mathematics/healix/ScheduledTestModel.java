package com.mathematics.healix;

public class ScheduledTestModel {
    private String title;
    private String description;
    private String category;
    private String date;
    private String time;
    private String address;
    private String pincode;
    private String amount;

    public ScheduledTestModel() {
        // Required empty constructor for Firestore
    }

    public ScheduledTestModel(String title, String description, String category, String date, String time, String address, String pincode, String amount) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.time = time;
        this.address = address;
        this.pincode = pincode;
        this.amount = amount;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getAddress() { return address; }
    public String getPincode() { return pincode; }
    public String getAmount() { return amount; }
}

