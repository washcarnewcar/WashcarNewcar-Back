package me.washcar.wcnc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.washcar.wcnc.entity.StoreReservationException;

public class ReservationException {

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReservationExceptionDto {

    private boolean allday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime end;

    public static ReservationExceptionDto from(
        StoreReservationException storeReservationException) {
      return ReservationExceptionDto.builder().allday(storeReservationException.isAllDay())
          .start(storeReservationException.getStartDateTime())
          .end(storeReservationException.getEndDateTime()).build();
    }
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReservationExceptionRequestDto {

    private List<ReservationExceptionDto> except;

  }

  @Getter
  @Builder
  public static class ReservationExceptionMessage {

    private int status;
    private String message;

    public static ReservationExceptionMessage from(int status, String message) {
      return ReservationExceptionMessage.builder().status(status).message(message).build();
    }
  }

  @Getter
  @Builder
  public static class ReservationExceptionResponseDto {

    private List<ReservationExceptionDto> except;

    public static ReservationExceptionResponseDto from(
        List<ReservationExceptionDto> storeReservationExceptions) {
      return ReservationExceptionResponseDto.builder().except(storeReservationExceptions).build();
    }
  }
}
