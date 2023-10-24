package com.vhws.karaoke.dto;

import com.vhws.karaoke.model.Music;
import jakarta.persistence.*;

import java.util.List;

public class PlayListDTO {
    private long playListId;
    private String name;
    private byte[] picture;
    private List<Music> songList;

    public PlayListDTO(long playListId, String name, byte[] picture, List<Music> songList) {
        this.playListId = playListId;
        this.name = name;
        this.picture = picture;
        this.songList = songList;
    }

    public PlayListDTO(String name, byte[] picture, List<Music> songList) {
        this.name = name;
        this.picture = picture;
    }

         public PlayListDTO() {
        }

        public long getPlayListId() {
            return playListId;
        }

        public void setPlayListId(long playListId) {
            this.playListId = playListId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Music> getSongList() {
            return songList;
        }

        public void setSongList(List<Music> songList) {
            this.songList = songList;
        }

        public byte[] getPicture() {
            return picture;
        }

        public void setPicture(byte[] picture) {
            this.picture = picture;
        }
}

