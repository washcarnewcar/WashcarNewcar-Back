package me.washcar.wcnc.dto.provider;

import static java.util.stream.Collectors.toList;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import me.washcar.wcnc.entity.StoreLocation;

public class StoreCreate {

  @Getter
  @AllArgsConstructor
  public static class isApprovedDto {

    private int status;
    private String message;
    private String reason;
  }

  @Getter
  @AllArgsConstructor
  public static class getSlugDto {

    private int status;
    private String message;
    private String slug;
  }

  @Getter
  @Builder
  public static class StoreAllInfoDto {

    private String name;
    private String tel;
    private CoordinateDto coordinate;
    private String address;
    private String address_detail;
    private String slug;
    private String wayto;
    private String description;
    private String preview_image;
    private List<String> store_image;

    public static StoreAllInfoDto from(Store store) {
      return StoreAllInfoDto.builder()
          .name(store.getName())
          .tel(store.getTel())
          .coordinate(CoordinateDto.from(store.getStoreLocation()))
          .address(store.getAddress())
          .address_detail(store.getAddressDetail())
          .slug(store.getSlug())
          .wayto(store.getWayTo())
          .description(store.getDescription())
          .preview_image(store.getPreviewImage())
          .store_image(
              store.getStoreImages().stream().map(StoreImage::getImageUrl).collect(toList()))
          .build();
    }
  }

  @Getter
  @Builder
  public static class CoordinateDto {

    private Double longitude;
    private Double latitude;

    public static CoordinateDto from(StoreLocation storeLocation) {
      return CoordinateDto.builder()
          .longitude(storeLocation.getLongitude())
          .latitude(storeLocation.getLatitude())
          .build();
    }
  }
}
