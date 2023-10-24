package com.vhws.karaoke.response;

import com.vhws.karaoke.model.Music;

public class UserResponse {
    private String name;
    private String lastName;
    private byte[] picture;
    private Music music;

    public UserResponse() {

    }

    public UserResponse(String name, String lastName, byte[] picture, Music music) {
        this.name = name;
        this.lastName = lastName;
        this.picture = picture;
        this.music = music;
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

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
