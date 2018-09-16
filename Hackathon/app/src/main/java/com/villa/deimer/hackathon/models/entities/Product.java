package com.villa.deimer.hackathon.models.entities;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("namec")
    private String name;
    @SerializedName("status_id")
    private int status;
    @SerializedName("val")
    private float value;
    @SerializedName("points")
    private int points;

    public Product() {}
    public Product(String name, int status, float value, int points) {
        this.name = name;
        this.status = status;
        this.value = value;
        this.points = points;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setValue(float value) {
        this.value = value;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getStatus() {
        return status;
    }
    public float getValue() {
        return value;
    }
    public int getPoints() {
        return points;
    }

    //ToString
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", value=" + value +
                ", points='" + points + '\'' +
                '}';
    }
}
