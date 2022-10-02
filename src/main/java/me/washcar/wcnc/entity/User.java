package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    private long userNumber;

    private String userId;

    private String email;

    private String password;

    private String nickname;

    private String role;

    private String provider;

    private String providerId;

    private ZonedDateTime createDateTime;

    private ZonedDateTime lastLoginDateTime;

    @OneToMany(mappedBy = "user")
    private List<User_Store> user_stores = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

}

