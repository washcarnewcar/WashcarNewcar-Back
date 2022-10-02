package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
public class StoreLocation {
    @Id
    @GeneratedValue
    private long storeLocationNumber;

    private String address;

    private String detailedAddress;

    private double latitude;

    private double longitude;

    @OneToOne
    private Store store;
}
