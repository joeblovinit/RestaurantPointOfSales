package com.example.restaurantpointofsales;

import java.io.Serializable;

public class food implements Serializable {

    //initialize attributes

   private int food_id;
   private String food_name;
   private String food_type;
   private String food_modifiers;
   private double price;

   //constructors


    public food(int food_id, String food_name, String food_type, String food_modifiers, double price) {
        this.food_id = food_id;
        this.food_name = food_name;
        this.food_type = food_type;
        this.food_modifiers = food_modifiers;
        this.price = price;
    }

    public food() {
    }




    //toString
    @Override
    public String toString() {
        return "food{" +
                "food_id='" + food_id + '\'' +
                ", food_name='" + food_name + '\'' +
                ", food_type='" + food_type + '\'' +
                ", food_modifiers='" + food_modifiers + '\'' +
                ", price=" + price +
                '}';
    }


    //getters and setters


    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_type() {
        return food_type;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public String getFood_modifiers() {
        return food_modifiers;
    }

    public void setFood_modifiers(String food_modifiers) {
        this.food_modifiers = food_modifiers;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
