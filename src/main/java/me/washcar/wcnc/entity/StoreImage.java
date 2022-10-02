package me.washcar.wcnc.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class StoreImage {
    @Id
    @GeneratedValue
    private long storeImageNumber;

    private String photoUrl;

    @ManyToOne
    private Store store;
}
