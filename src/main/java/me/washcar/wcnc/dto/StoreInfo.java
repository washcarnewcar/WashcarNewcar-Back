package me.washcar.wcnc.dto;

import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import lombok.Setter;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import me.washcar.wcnc.entity.StoreOption;

public class StoreInfo {

  //TODO 세차장리스트조회-Dto
  @Getter
  public static class searchStoreByLocationDto {

    private List<Object> store;
  }

  // 세차장정보-Dto
  @Getter
  @Builder
  public static class StoreInfoDto {

    private String preview_image;
    private List<String> images;
    private String name;

    public static StoreInfoDto from(Store store, List<StoreImage> storeImages) {
      return StoreInfoDto.builder()
          .preview_image(store.getPreviewImage())
          .images(storeImages.stream().map(StoreImage::getImageUrl).collect(Collectors.toList()))
          .name(store.getName())
          .build();
    }
  }

  // 세차장메뉴-Dto
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

  // 세차장세부정보-Dto
  @Getter
  @Builder
  public static class StoreDetailDto {

    private String tel;
    private String address;
    private String wayto;
    private String description;

    public static StoreDetailDto from(Store store) {
      return StoreDetailDto.builder()
          .tel(store.getTel())
          .address(store.getAddress())
          .wayto(store.getWayTo())
          .description(store.getDescription())
          .build();
    }
  }
}
