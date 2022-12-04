package me.washcar.wcnc.dto;

import lombok.Builder;
import lombok.Getter;
import me.washcar.wcnc.entity.StoreMenu;

public class Reservation {

  // 메뉴에대한정보-Dto
  @Getter
  @Builder
  public static class MenuInfoDto {

    private String image;
    private String name;
    private String detail;
    private int price;

    public static MenuInfoDto from(StoreMenu storeMenu) {
      return MenuInfoDto.builder()
          .image(storeMenu.getImage())
          .name(storeMenu.getName())
          .detail(storeMenu.getDescription())
          .price(storeMenu.getPrice())
          .build();
    }
  }

  //TODO 가능한날짜리스트-Dto
  @Getter
  public static class menuAvailableDateDto {

    private Object available_date;
  }

  //TODO 날짜별예약가능한시간리스트-Dto
  @Getter
  public static class menuAvailableTimeDto {

    private Object available_time;
  }

  //TODO 세차예약요청-Dto
  @Getter
  public static class reservationDto {

    private int status;
    private String message;
    private Object reservation_info;
  }
}
