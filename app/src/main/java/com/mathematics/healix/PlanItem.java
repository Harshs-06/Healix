package com.mathematics.healix;

public class PlanItem {
    private String title;
    private int imageResId;
    private String subtitle;
    private String description;

    public PlanItem(String title, int imageResId, String subtitle, String description) {
        this.title = title;
        this.imageResId = imageResId;
        this.subtitle = subtitle;
        this.description = description;
    }

    public String getTitle() { return title; }
    public int getImageResId() { return imageResId; }
    public String getSubtitle() { return subtitle; }
    public String getDescription() { return description; }
}

