package me.washcar.wcnc.dto.provider;

import lombok.Builder;
import lombok.Getter;

public class OperationHour {

  // 운영시간설정-Dto
  @Getter
  @Builder
  public static class SetOperationHourMessageDto {

    private int status;
    private String message;

    public static SetOperationHourMessageDto from(int status, String message) {
      return SetOperationHourMessageDto.builder()
          .status(status)
          .message(message)
          .build();
    }
  }
}
