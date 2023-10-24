package com.vhws.karaoke.model;

import jakarta.persistence.*;

@Entity
@Table(name="fav_music")
public class FavMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fav_music_id")
    private Long favMusicId;
    private String title;
    private String artist;
    private String album;
    private String link;

    public FavMusic() {
    }

    public FavMusic(Long favMusicId, String title, String artist, String album, String link) {
        this.favMusicId = favMusicId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
    }

    public FavMusic(String title, String artist, String album, String link ) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.link = link;
    }

    public Long getFavMusicId() {
        return favMusicId;
    }

    public void setFavMusicId(Long favMusicId) {
        this.favMusicId = favMusicId;
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
