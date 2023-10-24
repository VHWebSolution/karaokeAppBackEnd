package com.vhws.karaoke.response;

import java.util.Date;

public class UserResponseCostumer {
    private Long userId;
    private String name;
    private String lastName;
    private Date birthDay;
    private String gender;
    private String address;
    private String phone;
    private byte[] picture;

    public UserResponseCostumer(Long userId, String name, String lastName, Date birthDay, String gender, String address, String phone, byte[] picture) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
    }

    public UserResponseCostumer() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
