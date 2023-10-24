package com.vhws.karaoke.response;

public class UserResponseFriend {
    private String name;
    private String lastName;


    public UserResponseFriend(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public UserResponseFriend() {
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
}
