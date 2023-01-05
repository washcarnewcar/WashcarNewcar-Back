package me.washcar.wcnc.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import me.washcar.wcnc.form.NewStoreCreationForm;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

  @Builder
  public Store(String name, String tel, String address, String addressDetail, String slug,
      String wayTo,
      String description,
      String previewImage, Boolean isApproved, Boolean isChecked) {
    this.name = name;
    this.tel = tel;
    this.address = address;
    this.addressDetail = addressDetail;
    this.description = description;
    this.slug = slug;
    this.wayTo = wayTo;
    this.previewImage = previewImage;
    this.isApproved = isApproved;
    this.isChecked = isChecked;
  }

  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  private String name;

  private String tel;

  private String address;

  private String addressDetail;

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
  private List<StoreMenu> storeMenus = new ArrayList<>();

  @OneToOne
  private StoreLocation storeLocation;

  @OneToOne
  private StoreOperateTime storeOperateTime;

  public void setStore(NewStoreCreationForm form, StoreLocation storeLocation) {
    this.name = form.getName();
    this.tel = form.getTel();
    this.address = form.getAddress();
    this.addressDetail = form.getAddress_detail();
    this.slug = form.getSlug();
    this.wayTo = form.getWayto();
    this.description = form.getDescription();
    this.previewImage = form.getPreview_image();
    this.storeLocation = storeLocation;
  }
}
