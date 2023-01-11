package me.washcar.wcnc.dto;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.core.parameters.P;

public class StoreOperateTimeDto {

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class StoreOperateTimeRequestDto {

    private OperateTimeDto sunday;
    private OperateTimeDto monday;
    private OperateTimeDto tuesday;
    private OperateTimeDto wednesday;
    private OperateTimeDto thursday;
    private OperateTimeDto friday;
    private OperateTimeDto saturday;
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class StoreOperateTimeResponseDto {

    private OperateTimeDto sunday;
    private OperateTimeDto monday;
    private OperateTimeDto tuesday;
    private OperateTimeDto wednesday;
    private OperateTimeDto thursday;
    private OperateTimeDto friday;
    private OperateTimeDto saturday;

    public static StoreOperateTimeResponseDto from(OperateTimeDto sunday,
        OperateTimeDto monday,
        OperateTimeDto tuesday,
        OperateTimeDto wednesday,
        OperateTimeDto thursday,
        OperateTimeDto friday,
        OperateTimeDto saturday) {
      return StoreOperateTimeResponseDto.builder()
          .sunday(sunday)
          .monday(monday)
          .tuesday(tuesday)
          .wednesday(wednesday)
          .thursday(thursday)
          .friday(friday)
          .saturday(saturday)
          .build();
    }
  }

  @Getter
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class OperateTimeDto {

    @Nullable
    private LocalTime start;
    @Nullable
    private LocalTime end;
  }
}
