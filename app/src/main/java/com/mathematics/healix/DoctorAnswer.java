package com.mathematics.healix;

public class DoctorAnswer {
    private String doctorName;
    private String doctorSpecialist;
    private String responseTime;
    private String suggestion;

    public DoctorAnswer(String doctorName, String doctorSpecialist, String responseTime, String suggestion) {
        this.doctorName = doctorName;
        this.doctorSpecialist = doctorSpecialist;
        this.responseTime = responseTime;
        this.suggestion = suggestion;
    }

    public String getDoctorName() { return doctorName; }
    public String getDoctorSpecialist() { return doctorSpecialist; }
    public String getResponseTime() { return responseTime; }
    public String getSuggestion() { return suggestion; }
}

