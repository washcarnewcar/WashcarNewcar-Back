package me.washcar.wcnc.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Store {

  @Builder
  public Store(String name, String tel, String address, String slug, String wayTo,
      String description,
      String previewImage, Boolean isApproved, Boolean isChecked) {
    this.name = name;
    this.tel = tel;
    this.address = address;
    this.description = description;
    this.slug = slug;
    this.wayTo = wayTo;
    this.previewImage = previewImage;
    this.isApproved = isApproved;
    this.isChecked = isChecked;
  }

  @Id
  @GeneratedValue
  private Long storeId;

  @Column(nullable = false)
  private String name;

  private String tel;

  private String address;

  @Column(nullable = false, unique = true)
  private String slug;

  private String wayTo;

  private String description;

  private String previewImage;

  @Column(nullable = false)
  private Boolean isApproved;

  @Column(nullable = false)
  private Boolean isChecked;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
  private List<StoreImage> storeImages = new ArrayList<>();

  @OneToMany(mappedBy = "store")
  private List<Reservation> reservations = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
  private List<StoreOption> storeOptions = new ArrayList<>();

}
