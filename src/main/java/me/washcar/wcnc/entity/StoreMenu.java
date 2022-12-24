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
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuRequestDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreMenu {

  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  private String image;
  @NotNull
  private String name;
  @NotNull
  private String description;
  @NotNull
  private int price;
  @NotNull
  private Long expected_hour;
  @NotNull
  private Long expected_minute;
  @ManyToOne
  private Store store;

  public void updateStoreMenu(MenuRequestDto menuRequestDto) {
    this.setImage(menuRequestDto.getImage());
    this.setName(menuRequestDto.getName());
    this.setDescription(menuRequestDto.getDescription());
    this.setExpected_hour(menuRequestDto.getExpected_hour());
    this.setExpected_minute(menuRequestDto.getExpected_minute());
    this.setPrice(menuRequestDto.getPrice());
  }
}
