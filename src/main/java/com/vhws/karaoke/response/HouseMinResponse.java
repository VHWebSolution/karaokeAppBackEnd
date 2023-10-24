package com.vhws.karaoke.response;

import com.vhws.karaoke.model.User;

import java.util.List;

public class HouseMinResponse {
    private Long houseId;
    private String houseName;
    private String cnpj;
    private String description;
    private List<User> costumersIn;
    private String operatingHours;
    private String address;
    private String phone;
    private byte[] picture;

    public HouseMinResponse() {

    }

    public HouseMinResponse(Long houseId, String houseName, String description, String operatingHours, String address, String phone, byte[] picture) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.description = description;
        this.costumersIn = costumersIn;
        this.operatingHours = operatingHours;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
    }
    public HouseMinResponse(Long houseId, String houseName, String cnpj, String description, String operatingHours, String address, String phone, byte[] picture) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.cnpj = cnpj;
        this.description = description;
        this.operatingHours = operatingHours;
        this.address = address;
        this.phone = phone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getCostumersIn() {
        return costumersIn;
    }

    public void setCostumersIn(List<User> costumersIn) {
        this.costumersIn = costumersIn;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
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
