package com.vhws.karaoke.model;

import jakarta.persistence.*;

import java.util.List;

/*
* Casa:
- Nome;
- CNPJ;
- Descrição;
- Telefone;
- Endereço;
- Horario de funcionamento;
- Foto;
- Pessoas no bar;
- Aberto;
- Lista de musicas em aberto;
- Lista de musicas fechadas;
* */
@Entity
@Table(name="house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long houseId;
    private String houseName;
    private String cnpj;
    private String description;
    private String phone;
    private String address;
    private String operatingHours;
    private byte[] picture;
    @OneToMany(mappedBy = "house")
    private List<User> costumersIn;
    private boolean open;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "music_in_list", joinColumns = {
            @JoinColumn(name = "house_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "music_in_id")
    })
    private List<MusicIn> musicInList;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "music_out_list", joinColumns = {
            @JoinColumn(name = "house_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "music_out_id")
    })
    private List<MusicOut> musicOutOfList;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "house_singer", joinColumns = {
            @JoinColumn(name = "house_id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "user_id")
    })
    private List<User> nextSingerList;

    public House() {

    }

    public House(Long houseId, String houseName, String cnpj, String description, String phone, String address, String operatingHours, byte[] picture, List<User> costumersIn, boolean open, List<MusicIn> musicInList, List<MusicOut> musicOutOfList, List<User> nextSingerList) {
        this.houseId = houseId;
        this.houseName = houseName;
        this.cnpj = cnpj;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.operatingHours = operatingHours;
        this.picture = picture;
        this.costumersIn = costumersIn;
        this.open = open;
        this.musicInList = musicInList;
        this.musicOutOfList = musicOutOfList;
        this.nextSingerList = nextSingerList;
    }
    public House(String houseName, String cnpj, String description, String phone, String address, String operatingHours, byte[] picture) {
        this.houseName = houseName;
        this.cnpj = cnpj;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.operatingHours = operatingHours;
        this.picture = picture;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<User> getCostumersIn() {
        return costumersIn;
    }

    public void setCostumersIn(List<User> costumersIn) {
        this.costumersIn = costumersIn;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<MusicIn> getMusicInList() {
        return musicInList;
    }

    public void setMusicInList(List<MusicIn> musicInList) {
        this.musicInList = musicInList;
    }

    public List<MusicOut> getMusicOutOfList() {
        return musicOutOfList;
    }

    public void setMusicOutOfList(List<MusicOut> musicOutOfList) {
        this.musicOutOfList = musicOutOfList;
    }

    public List<User> getNextSingerList() {
        return nextSingerList;
    }

    public void setNextSingerList(List<User> nextSingerList) {
        this.nextSingerList = nextSingerList;
    }
}
