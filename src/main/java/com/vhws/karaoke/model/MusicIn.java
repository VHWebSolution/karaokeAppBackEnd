package com.vhws.karaoke.model;

import jakarta.persistence.*;

@Entity
@Table(name="music_in")
public class MusicIn{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_in_id")
    private Long musicInId;
    private String title;
    private String artist;
    private String album;
    private String link;

    public MusicIn() {
    }

    public MusicIn(Long musicInId, String title, String artist, String album, String link) {
        this.musicInId = musicInId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
    }
    public MusicIn(String title, String artist, String album, String link) {

        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
    }

    public Long getMusicInId() {
        return musicInId;
    }

    public void setMusicInId(Long musicInId) {
        this.musicInId = musicInId;
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
