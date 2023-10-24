package com.vhws.karaoke.response;

public class CheckInResponse {
    private long houseId;
    private long userId;
    private String houseName;
    private String userName;

    public CheckInResponse(long houseId, long userId, String houseName, String userName) {
        this.houseId = houseId;
        this.userId = userId;
        this.houseName = houseName;
        this.userName = userName;
    }

    public CheckInResponse() {
    }

    public long getHouseId() {
        return houseId;
    }

    public void setHouseId(long houseId) {
        this.houseId = houseId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
