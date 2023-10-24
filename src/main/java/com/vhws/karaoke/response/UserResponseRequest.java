package com.vhws.karaoke.response;

public class UserResponseRequest {
    private Long userId;
    private String name;
    private String lastName;

    public UserResponseRequest(Long userId, String name, String lastName) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
    }

    public UserResponseRequest() {
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
}
