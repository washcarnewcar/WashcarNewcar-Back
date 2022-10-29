package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class User_Store {
    @Id
    @GeneratedValue
    private Long user_storeId;

    @ManyToOne
    private Store store;

    @ManyToOne
    private User user;

}
