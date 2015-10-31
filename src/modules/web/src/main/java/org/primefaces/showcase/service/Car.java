package org.primefaces.showcase.service;

/**
 * Created by gforrade on 7/18/15.
 * Copyright (c) 2015, DATASTAR S.A.
 */
public class Car {
    private int year;
    private String id;
    private String brand;
    private String color;
    private int price;
    private  boolean state;

    public Car(String id, String brand,int year,  String color, int price, boolean state) {
        this.year = year;
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.state = state;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
