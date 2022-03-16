package com.example.restaurantpointofsales;

import java.io.Serializable;

public class table implements Serializable {

    //attributes

    private int table_id;
    private int table_num;
    private int table_seats;

    //constructors

    public table(int table_id, int table_num, int table_seats) {
        this.table_id = table_id;
        this.table_num = table_num;
        this.table_seats = table_seats;
    }

    public table() {
    }

    //getters and setters

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getTable_num() {
        return table_num;
    }

    public void setTable_num(int table_num) {
        this.table_num = table_num;
    }

    public int getTable_seats() {
        return table_seats;
    }

    public void setTable_seats(int table_seats) {
        this.table_seats = table_seats;
    }
}
