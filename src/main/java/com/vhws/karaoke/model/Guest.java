package com.vhws.karaoke.model;

import jakarta.persistence.*;

import java.util.Date;

/*
* - Nome;
- Telefone;
- Data nascimento;
* */
@Entity
@Table(name="guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_id")
    private Long guestId;
    private String guestName;
    private String phone;
    private Date birthday;

    public Guest() {

    }

    public Guest(Long guestId, String guestName, String phone, Date birthday) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phone = phone;
        this.birthday = birthday;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
