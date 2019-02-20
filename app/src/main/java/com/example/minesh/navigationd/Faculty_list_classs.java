package com.example.minesh.navigationd;

public class Faculty_list_classs {

    public int image;
    public String name,designation;
    public Faculty_list_classs(int image, String name, String designation) {
        this.image = image;
        this.name = name;
        this.designation=designation;
    }



    public int gImage() {
        return image;
    }

    public void sImage(int image) {
        this.image = image;
    }

    public String gName() {
        return name;
    }

    public void sName(String name) {
        this.name = name;
    }

    public String gDesignation() {
        return designation;
    }

    public void sDesignation(String designation) {
        this.designation = designation;
    }
}
