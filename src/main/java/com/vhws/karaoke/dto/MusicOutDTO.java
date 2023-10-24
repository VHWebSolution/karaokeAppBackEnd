package com.vhws.karaoke.dto;

public class MusicOutDTO {
    private Long musicOutId;
    private String title;
    private String artist;
    private String album;
    private String link;

    public MusicOutDTO() {
    }

    public MusicOutDTO(Long musicOutId, String title, String artist, String album, String link) {
        this.musicOutId = musicOutId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
    }
    public MusicOutDTO(String title, String artist, String album, String link) {

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
