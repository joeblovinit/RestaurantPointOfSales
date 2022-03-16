package com.example.restaurantpointofsales;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class foodOrder implements Serializable {

    //initialize attributes

    private int order_id;
    private String starter;
    private String main;
    private String dessert;
    private String side;
    private String drink;
    private String staff_name;
    private int table_num;
    private String time_ordered;
    private Double price;

    //constructors

    public foodOrder(int order_id, String starter, String main, String dessert, String side, String drink, String staff_name, int table_num, String time_ordered, Double price) {
        this.order_id = order_id;
        this.starter = starter;
        this.main = main;
        this.dessert = dessert;
        this.side = side;
        this.drink = drink;
        this.staff_name = staff_name;
        this.table_num = table_num;
        this.time_ordered = time_ordered;
        this.price = price;
    }

    public foodOrder() {
    }

    //getters and setters

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public int getTable_num() {
        return table_num;
    }

    public void setTable_num(int table_num) {
        this.table_num = table_num;
    }

    public String getTime_ordered() {
        return time_ordered;
    }

    public void setTime_ordered(String time_ordered) {
        this.time_ordered = time_ordered;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
