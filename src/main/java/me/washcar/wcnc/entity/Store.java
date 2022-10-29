package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Store {
    @Id
    @GeneratedValue
    private Long storeId;

    private String name;

    private String profileUrl;

    private String info;

    private String wayTo;

    private String registrationNumber;

    @OneToOne(mappedBy = "store")
    private StoreLocation storeLocation;

    @OneToMany(mappedBy = "store")
    private List<StoreImage> storeImages = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<User_Store> user_stores = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Slot> slots = new ArrayList<>();
}
