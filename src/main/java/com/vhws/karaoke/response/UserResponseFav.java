package com.vhws.karaoke.response;

import com.vhws.karaoke.model.FavMusic;

import java.util.List;

public class UserResponseFav {
    private String name;
    private String lastName;
    private List<FavMusic> favMusicList;

    public UserResponseFav(String name, String lastName, List<FavMusic> favMusicList) {
        this.name = name;
        this.lastName = lastName;
        this.favMusicList = favMusicList;
    }

    public UserResponseFav() {
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

    public List<FavMusic> getFavMusicList() {
        return favMusicList;
    }

    public void setFavMusicList(List<FavMusic> favMusicList) {
        this.favMusicList = favMusicList;
    }
}
