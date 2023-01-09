package me.washcar.wcnc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import me.washcar.wcnc.entity.Reservation;
import me.washcar.wcnc.entity.StoreMenu;

public class ReservationDto {

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
  @Builder
  public static class ReservationResultDto {

    private int status;
    private String message;
    private ReservationInfo reservation_info;

    public static ReservationResultDto from(int status, String message, Reservation reservation) {
      if (reservation == null) {
        return ReservationResultDto.builder()
            .status(status)
            .message(message)
            .reservation_info(null)
            .build();
      }
      return ReservationResultDto.builder()
          .status(status)
          .message(message)
          .reservation_info(ReservationInfo.builder()
              .car_model(reservation.getModel().getName())
              .store_name(reservation.getStore().getName())
              .date(reservation.getDate())
              .price(80000) // TODO : 요금 로직 정해서 수정하기
              .build())
          .build();
    }
  }

  @Getter
  @Builder
  public static class ReservationInfo {

    private String car_model;
    private String store_name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private int price;
  }

  @Getter
  public static class ReservationRequestDto {

    private String tel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    private String request;

    private int car_model_number;

    private String car_number;

  }
}
