package me.washcar.wcnc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import lombok.Setter;
import me.washcar.wcnc.entity.Reservation;

public class ReservationInfo {
  // 휴대폰번호로예약리스트조회-Dto

  @Getter
  @Setter
  public static class ReservationWithTelListResult<T> {

    private List<T> list;

    public static <T> ReservationWithTelListResult<T> getReservationListResult(List<T> list) {
      ReservationWithTelListResult<T> result = new ReservationWithTelListResult<>();
      result.setList(list);
      return result;
    }
  }

  @Getter
  @Builder
  public static class ReservationWithTelDto {

    private String status;
    private Long reservation_number;
    private String car_model;
    private String car_number;
    private String store;
    private String preview_image;
    private String menu;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    public static ReservationWithTelDto from(Reservation reservation) {
      return ReservationWithTelDto.builder()
          .status(reservation.getReservationStatus().getType())
          .reservation_number(reservation.getId())
          .car_model(reservation.getModel().getName())
          .car_number(reservation.getCarNumber())
          .store(reservation.getStore().getName())
          .preview_image(reservation.getStore().getPreviewImage())
          .menu(reservation.getStoreMenu().getName())
          .date(reservation.getDate())
          .build();
    }

  }

  // 예약한세차상세조회-Dto
  @Getter
  @Builder
  public static class ReservationDetailDto {

    private String status;
    private String car_model;
    private String car_number;
    private String store_name;
    private String store_slug;
    private String menu;
    private int price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime expected_wash_time;

    public static ReservationDetailDto from(Reservation reservation) {
      return ReservationDetailDto.builder()
          .status(reservation.getReservationStatus().getType())
          .car_model(reservation.getModel().getName())
          .car_number(reservation.getCarNumber())
          .store_name(reservation.getStore().getName())
          .store_slug(reservation.getStore().getSlug())
          .menu(reservation.getStoreMenu().getName())
          .price(reservation.getStoreMenu().getPrice())
          .date(reservation.getDate())
          .expected_wash_time(reservation.getExpectedWashTime())
          .build();
    }

  }
}
