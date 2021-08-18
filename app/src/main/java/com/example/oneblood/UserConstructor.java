package com.example.oneblood;

public class UserConstructor {

    private String fullName, email, address, password , bloodgroup,phone;
    private Integer age;
    private Boolean donor;

    public UserConstructor() {
    }

    @Override
    public String toString() {
        return "UserConstructor{" +
                "fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", bloodgroup='" + bloodgroup + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", donor=" + donor +
                '}';
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getDonor() {
        return donor;
    }

    public void setDonor(Boolean donor) {
        this.donor = donor;
    }

    public UserConstructor(String fullName,String password, String bloodgroup, String email, String address,  String phone, Integer age, Boolean donor) {
        this.fullName = fullName;
        this.password = password;
        this.bloodgroup = bloodgroup;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.donor = donor;
    }
}