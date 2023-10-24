package com.vhws.karaoke.dto;

import com.vhws.karaoke.model.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class UserDTO {
    private Long userId;
    private String name;
    private String lastName;
    private Date birthDay;
    private String gender;
    private String cpf;
    private String address;
    private String phone;

    private List<PlayList> playList;

    private List<FavMusic> favMusicList;
    private byte[] picture;

    private Music nextMusic;

    private House house;

    private List<User> friends;
    private List<User> requestDispatched;

    private List<User> requestReceived;

    public UserDTO() {

    }

    public UserDTO(Long userId, String name, String lastName, Date birthDay, String gender, String cpf, String address, String phone, List<PlayList> playList, List<FavMusic> favMusicList, byte[] picture, Music nextMusic, House house, List<User> friends, List<User> requestDispatched, List<User> requestReceived) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.cpf = cpf;
        this.address = address;
        this.phone = phone;
        this.playList = playList;
        this.favMusicList = favMusicList;
        this.picture = picture;
        this.nextMusic = nextMusic;
        this.house = house;
        this.friends = friends;
        this.requestDispatched = requestDispatched;
        this.requestReceived = requestReceived;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public List<FavMusic> getFavMusicList() {
        return favMusicList;
    }

    public void setFavMusicList(List<FavMusic> favMusicList) {
        this.favMusicList = favMusicList;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Music getNextMusic() {
        return nextMusic;
    }

    public void setNextMusic(Music nextMusic) {
        this.nextMusic = nextMusic;
    }

    public List<PlayList> getPlayList() {
        return playList;
    }

    public void setPlayList(List<PlayList> playList) {
        this.playList = playList;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getRequestDispatched() {
        return requestDispatched;
    }

    public void setRequestDispatched(List<User> requestDispatched) {
        this.requestDispatched = requestDispatched;
    }

    public List<User> getRequestReceived() {
        return requestReceived;
    }

    public void setRequestReceived(List<User> requestReceived) {
        this.requestReceived = requestReceived;
    }
}
