package me.washcar.wcnc.dto.provider;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

import lombok.Setter;
import me.washcar.wcnc.entity.Reservation;

public class ReservationCheck {

  // 세차예약요청리스트-Dto
  @Getter
  @Setter
  public static class ReservationListResult<T> {

    private List<T> list;

    public static <T> ReservationListResult<T> getReservationListResult(List<T> list) {
      ReservationListResult<T> result = new ReservationListResult<>();
      result.setList(list);
      return result;
    }
  }

  @Getter
  @Builder
  public static class RequestReservationDto {

    private Long reservation_number;

    private String menu;

    private String car_number;

    private String car_model;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;

    public static RequestReservationDto from(Reservation reservation) {
      return RequestReservationDto.builder()
          .reservation_number(reservation.getId())
          .menu(reservation.getStoreMenu().getName())
          .car_number(reservation.getCarNumber())
          .car_model(reservation.getModel().getName())
          .date(reservation.getDate())
          .build();
    }
  }

  // 세차예약요청상세-Dto
  @Getter
  @Builder
  public static class ReservationDetailDto {

    private String type;
    private String car_number;
    private String car_model;
    private String store;
    private String menu;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end_date;

    public static ReservationDetailDto from(Reservation reservation) {
      return ReservationDetailDto.builder()
          .type(reservation.getReservationStatus().getType())
          .car_number(reservation.getCarNumber())
          .car_model(reservation.getModel().getName())
          .store(reservation.getStore().getName())
          .menu(reservation.getStoreMenu().getName())
          .date(reservation.getDate())
          .end_date(reservation.getEndDate())
          .build();
    }

  }

  //TODO 예약요청확인-Dto
  //TODO 예약요청거부-Dto
  @Getter
  @Builder
  public static class ApproveResponseDto {

    private int status;
    private String message;

    public static ApproveResponseDto from(int status, String message) {
      return ApproveResponseDto.builder()
          .status(status)
          .message(message)
          .build();
    }
  }

  @Getter
  public static class ApproveRequestDto {

    private LocalTime expected_wash_time;
  }
}
