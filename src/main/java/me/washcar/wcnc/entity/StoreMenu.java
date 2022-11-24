package me.washcar.wcnc.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Setter;
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreMenu {

  @Id
  @GeneratedValue
  private Long storeMenuId;
  @NotNull
  private String image;
  @NotNull
  private String name;
  @NotNull
  private String description;
  @NotNull
  private int price;
  @NotNull
  private String expected_time;

  @ManyToOne
  private Store store;

  public void updateStoreMenu(MenuDto menuDto) {
    this.setImage(menuDto.getImage());
    this.setName(menuDto.getName());
    this.setDescription(menuDto.getDescription());
    this.setExpected_time(menuDto.getExpected_time());
    this.setPrice(menuDto.getPrice());
  }
}
