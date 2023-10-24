package com.vhws.karaoke.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "playlist")
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "play_list_id")
    private long playListId;
    private String name;
    private byte[] picture;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "playlist_music", joinColumns = {
            @JoinColumn(name = "play_list_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "music_id")
    })
    private List<Music> songList;

    public PlayList(long playListId, String name, byte[] picture, List<Music> songList) {
        this.playListId = playListId;
        this.name = name;
        this.picture = picture;
        this.songList = songList;
    }

    public PlayList(String name, byte[] picture, List<Music> songList) {
        this.name = name;
        this.picture = picture;
        this.songList = songList;
    }

    public PlayList() {
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
