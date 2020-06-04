package com.example.forms.activity;

public class ProfileDetails {

    String email;
    String name;
    String mobile_Num;
    String collegeName;

    public ProfileDetails(String email, String name, String mobile_Num, String collegeName) {
        this.email = email;
        this.name = name;
        this.mobile_Num = mobile_Num;
        this.collegeName = collegeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_Num() {
        return mobile_Num;
    }

    public void setMobile_Num(String mobile_Num) {
        this.mobile_Num = mobile_Num;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
