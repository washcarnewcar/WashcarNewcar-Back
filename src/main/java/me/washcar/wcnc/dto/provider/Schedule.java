package me.washcar.wcnc.dto.provider;

import java.util.List;
import lombok.Getter;

import java.time.ZonedDateTime;
import lombok.Setter;

public class Schedule {

  // 세차스케줄리스트-Dto

  @Getter
  @Setter
  public static class ScheduleListResult<T> {

    private List<T> list;

    public static <T> ScheduleListResult<T> getScheduleListResult(
        List<T> list) {
      ScheduleListResult<T> result = new ScheduleListResult<>();
      result.setList(list);
      return result;
    }
  }

  //TODO 세차스케줄상세-Dto
  @Getter
  public static class detailDto {

    private String menu;
    private String car_model;
    private String car_number;
    private ZonedDateTime date;
    private ZonedDateTime required_time;
    private String request;
  }

  //TODO 세차스케줄거부-Dto
  @Getter
  public static class deleteDto {

    private int status;
    private String message;
  }
}
