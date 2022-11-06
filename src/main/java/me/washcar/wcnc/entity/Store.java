package me.washcar.wcnc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Builder
    public Store(String name, String tel, String slug, String wayTo, String description, String previewImage, Boolean isApproved, StoreLocation storeLocation) {
        this.name = name;
        this.tel = tel;
        this.slug = slug;
        this.wayTo = wayTo;
        this.description = description;
        this.previewImage = previewImage;
        this.isApproved = isApproved;
    }

    @Id
    @GeneratedValue
    private Long storeId;
    @Column(nullable = false)
    private String name;

    private String tel;

    @Column(nullable = false, unique = true)
    private String slug;

    private String wayTo;

    private String description;

    private String previewImage;

    @Column(nullable = false)
    private Boolean isApproved;

    @OneToOne(mappedBy = "store")
    private StoreLocation storeLocation;

    @OneToMany(mappedBy = "store")
    private List<StoreImage> storeImages = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<User_Store> user_stores = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Slot> slots = new ArrayList<>();
}
