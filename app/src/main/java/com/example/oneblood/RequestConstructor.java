package com.example.oneblood;

public class RequestConstructor {

    private int id;
    private String donee, bloodgroup, address ,patientPhone,date;
    private int age;

    public RequestConstructor() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDonee() {
        return donee;
    }

    public void setDonee(String donee) {
        this.donee = donee;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RequestConstructor(int id, String donee, String bloodgroup, String address, String patientPhone, String date, int age) {
        this.id = id;
        this.donee = donee;
        this.bloodgroup = bloodgroup;
        this.address = address;
        this.patientPhone = patientPhone;
        this.date = date;
        this.age = age;
    }

    @Override
    public String toString() {
        return "RequestConstructor{" +
                "id=" + id +
                ", donee='" + donee + '\'' +
                ", bloodgroup='" + bloodgroup + '\'' +
                ", address='" + address + '\'' +
                ", patientPhone='" + patientPhone + '\'' +
                ", date='" + date + '\'' +
                ", age=" + age +
                '}';
    }
}
