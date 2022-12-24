package me.washcar.wcnc.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import me.washcar.wcnc.form.NewStoreCreationForm;

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
  private Long id;

  private String address;

  private Double latitude;

  private Double longitude;

  @OneToOne
  private Store store;

  public void setStoreLocation(NewStoreCreationForm form, Store store) {
    this.address = form.getAddress();
    this.latitude = form.getCoordinate().getLatitude();
    this.longitude = form.getCoordinate().getLongitude();
    this.store = store;
  }
}
