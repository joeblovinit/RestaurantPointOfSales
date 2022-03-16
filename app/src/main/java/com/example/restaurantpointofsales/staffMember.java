package com.example.restaurantpointofsales;

import java.io.Serializable;

public class staffMember implements Serializable {
    //attributes
    private int staffId;
    private String staffRole;
    private String staffName;
    private String staffSurname;
    private String staffUsername;
    private String staffPass;
    private int staffTimeWorked;


    //constructors

    public staffMember(int staffId, String staffRole, String staffName, String staffSurname, String staffUsername, String staffPass, int staffTimeWorked) {
        this.staffId = staffId;
        this.staffRole = staffRole;
        this.staffName = staffName;
        this.staffSurname = staffSurname;
        this.staffUsername = staffUsername;
        this.staffPass = staffPass;
        this.staffTimeWorked = staffTimeWorked;
    }

    public staffMember() {
    }

    //getters and setters

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffSurname() {
        return staffSurname;
    }

    public void setStaffSurname(String staffSurname) {
        this.staffSurname = staffSurname;
    }

    public String getStaffUsername() {
        return staffUsername;
    }

    public void setStaffUsername(String staffUsername) {
        this.staffUsername = staffUsername;
    }

    public String getStaffPass() {
        return staffPass;
    }

    public void setStaffPass(String staffPass) {
        this.staffPass = staffPass;
    }

    public int getStaffTimeWorked() {
        return staffTimeWorked;
    }

    public void setStaffTimeWorked(int staffTimeWorked) {
        this.staffTimeWorked = staffTimeWorked;
    }
}
