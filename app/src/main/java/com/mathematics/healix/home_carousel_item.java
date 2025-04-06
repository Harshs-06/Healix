package com.mathematics.healix;

public class home_carousel_item {
    private String time;
    private String category;
    private String place;
    private String problem;
    private String detail;
    public home_carousel_item(String time, String category, String place, String problem, String detail){
        this.time = time;
        this.category = category;
        this.place = place;
        this.problem = problem;
        this.detail = detail;
    }

    public String getTime() { return time; }
    public String getCategory() { return category; }
    public String getPlace() { return place; }
    public String getProblem() { return problem; }
    public String getDetail() { return detail; }
}
