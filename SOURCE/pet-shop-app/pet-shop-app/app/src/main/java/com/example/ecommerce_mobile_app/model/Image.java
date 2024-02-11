package com.example.ecommerce_mobile_app.model;

import java.io.Serializable;

public class Image implements Serializable{
    private String imagePath;

    public Image(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}