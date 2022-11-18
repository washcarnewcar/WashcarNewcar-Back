package me.washcar.wcnc.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import lombok.Setter;
import me.washcar.wcnc.dto.VehicleDataDto.BrandListResult;
import me.washcar.wcnc.entity.StoreOption;

public class StoreInfo {

  //TODO 세차장리스트조회-Dto
  @Getter
  public static class searchStoreByLocationDto {

    private List<Object> store;
  }

  //TODO 세차장정보-Dto
  @Getter
  public static class StoreInfoDto {

    private String preview_image;
    private List<String> images;
    private String name;
  }

  //TODO 세차장메뉴-Dto
  @Getter
  @Builder
  public static class StoreMenuDto {

    private Long number;
    private String image;
    private String name;
    private String detail;
    private int price;

    public static StoreMenuDto from(StoreOption storeOption) {
      return StoreMenuDto.builder().number(storeOption.getStoreOptionId())
          .image(storeOption.getImage())
          .name(storeOption.getName())
          .detail(storeOption.getDescription())
          .price(storeOption.getPrice())
          .build();
    }
  }

  @Getter
  @Setter
  public static class MenuListResult<T> {

    private List<T> menu;

    public static <T> MenuListResult<T> getMenuListResult(List<T> list) {
      MenuListResult<T> result = new MenuListResult<>();
      result.setMenu(list);
      return result;
    }
  }

  //TODO 세차장세부정보-Dto
  @Getter
  public static class storeDetailDto {

    private String phone;
    private String address;
    private String wayto;
    private String info;
  }
}
