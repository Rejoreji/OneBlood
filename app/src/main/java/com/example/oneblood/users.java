package com.example.oneblood;

import java.io.Serializable;

public class users{

    String fullname, age, bloodGroup, address, phone,date;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public users(String fullname, String age, String bloodGroup, String address, String phone, String date) {
        this.fullname = fullname;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.phone = phone;
        this.date = date;
    }
}
