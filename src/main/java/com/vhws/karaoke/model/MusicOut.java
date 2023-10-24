package com.vhws.karaoke.model;

import jakarta.persistence.*;

@Entity
@Table(name="music_out")
public class MusicOut{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_out_id")
    private Long musicOutId;
    private String title;
    private String artist;
    private String album;
    private String link;

    public MusicOut() {
    }

    public MusicOut(Long musicOutId, String title, String artist, String album, String link) {
        this.musicOutId = musicOutId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
    }
    public MusicOut(String title, String artist, String album, String link) {

        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
    }

    public Long getMusicOutId() {
        return musicOutId;
    }

    public void setMusicOutId(Long musicOutId) {
        this.musicOutId = musicOutId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
