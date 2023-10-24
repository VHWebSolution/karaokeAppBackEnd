package com.vhws.karaoke.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String name;
    private String lastName;
    private Date birthDay;
    private String gender;
    private String cpf;
    private String address;
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_playlist", joinColumns = {
            @JoinColumn(name = "user_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "play_list_id")
    })
    private List<PlayList> playLists;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_fav_music", joinColumns = {
            @JoinColumn(name = "user_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "fav_music_id")
    })
    private List<FavMusic> favMusicList;
    private byte[] picture;
    @ManyToOne
    @JoinColumn(name = "next_music_id")
    private Music nextMusic;
    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
    @OneToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friends_id"))
    private List<User> friends;
    @OneToMany
    @JoinTable(name = "request_dispatched",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "request_dispatched_id"))
    private List<User> requestDispatched;
    @OneToMany
    @JoinTable(name = "request_received",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "request_received_id"))
    private List<User> requestReceived;

    public User() {

    }

    public User(Long userId, String name, String lastName, Date birthDay, String gender, String cpf, String address, String phone, List<PlayList> playList, List<FavMusic> favMusicList, byte[] picture, Music nextMusic, House house, List<User> friends) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.cpf = cpf;
        this.address = address;
        this.phone = phone;
        this.playLists = playList;
        this.favMusicList = favMusicList;
        this.picture = picture;
        this.nextMusic = nextMusic;
        this.house = house;
        this.friends = friends;
    }
    public User(String name, String lastName, Date birthDay, String gender, String cpf, String address, String phone, byte[] picture) {
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.cpf = cpf;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
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

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<FavMusic> getFavMusicList() {
        return favMusicList;
    }

    public void setFavMusicList(List<FavMusic> favMusicList) {
        this.favMusicList = favMusicList;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Music getNextMusic() {
        return nextMusic;
    }

    public void setNextMusic(Music nextMusic) {
        this.nextMusic = nextMusic;
    }

    public List<PlayList> getPlayList() {
        return playLists;
    }

    public void setPlayList(List<PlayList> playList) {
        this.playLists = playList;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getRequestDispatched() {
        return requestDispatched;
    }

    public void setRequestDispatched(List<User> requestDispatched) {
        this.requestDispatched = requestDispatched;
    }

    public List<User> getRequestReceived() {
        return requestReceived;
    }

    public void setRequestReceived(List<User> requestReceived) {
        this.requestReceived = requestReceived;
    }
}
