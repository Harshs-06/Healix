package com.mathematics.healix;

public class LabChoice_Model {
    int imageResId;
    String title;

    public LabChoice_Model(int imageResId,String title) {
        this.imageResId=imageResId;
        this.title = title ;
    }

    public int getImageResId() {return imageResId;}
    public String getTitle(){ return title;}
}
