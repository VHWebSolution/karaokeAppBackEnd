package com.vhws.karaoke.request;

public class HouseRequestAdd {
    private String houseName;
    private String cnpj;
    private String description;
    private String phone;
    private String address;
    private String operatingHours;
    private byte[] picture;

    public HouseRequestAdd() {
    }

    public HouseRequestAdd(String houseName, String cnpj, String description, String phone, String address, String operatingHours, byte[] picture) {
        this.houseName = houseName;
        this.cnpj = cnpj;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.operatingHours = operatingHours;
        this.picture = picture;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
