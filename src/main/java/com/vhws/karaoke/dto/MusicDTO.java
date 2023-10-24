package com.vhws.karaoke.dto;

public class MusicDTO {
    private Long musicId;
    private String title;
    private String artist;
    private String album;
    private String link;

    public MusicDTO() {
    }

    public MusicDTO(Long musicId, String title, String artist, String album, String link) {
        this.musicId = musicId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
    }
    public MusicDTO(Long musicId, String title, String artist, String album) {
        this.musicId = musicId;
        this.title = title;
        this.artist = artist;
        this.album = album;

    }

    public Long getMusicId() {
        return musicId;
    }

    public void setMusicId(Long musicId) {
        this.musicId = musicId;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
