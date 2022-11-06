package me.washcar.wcnc.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class StoreImage {
    @Builder
    public StoreImage(String imageUrl, Store store) {
        this.imageUrl = imageUrl;
        this.store = store;
    }

    @Id
    @GeneratedValue
    private Long storeImageId;

    private String imageUrl;

    @ManyToOne
    private Store store;
}
