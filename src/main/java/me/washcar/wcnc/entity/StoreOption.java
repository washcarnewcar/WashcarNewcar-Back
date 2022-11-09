package me.washcar.wcnc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreOption {
    @Id
    @GeneratedValue
    private Long storeOptionId;

    private String image;

    private String name;

    private String description;

    private int price;

    private String expected_time;

    @ManyToOne
    private Store store;
}
