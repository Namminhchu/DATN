package com.example.ecommerce_mobile_app.model;

import androidx.databinding.BaseObservable;

import java.io.Serializable;
import java.util.List;

public class Service extends BaseObservable implements Serializable {
    private int id;
    private String name;
    private float price;

    private String description;
    private List<Image> images;
    private List<Description> details;
    private String image;

    private int averageRating;

    private boolean isFav;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Description> getDetails() {
        return details;
    }

    public void setDetails(List<Description> details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public boolean isFav() {
        return isFav;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
