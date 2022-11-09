package me.washcar.wcnc.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
public class StoreLocation {
    @Builder
    public StoreLocation(String address, Double latitude, Double longitude, Store store) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.store = store;
    }

    @Id
    @GeneratedValue
    private Long storeLocationId;

    private String address;

    private Double latitude;

    private Double longitude;

    @OneToOne
    private Store store;
}
