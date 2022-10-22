package me.washcar.wcnc.dto.provider;

import lombok.Getter;

import java.time.ZonedDateTime;

public class ReservationCheck {

    //TODO 세차예약요청리스트-Dto
    @Getter
    public static class listDto {
        private Object list;
    }

    //TODO 세차예약요청상세-Dto
    @Getter
    public static class detailDto {
        private String menu;
        private String car_model;
        private String car_number;
        private ZonedDateTime date;
        private String request;
    }

    //TODO 예약요청확인-Dto
    //TODO 예약요청거부-Dto
    @Getter
    public static class responseDto {
        private int status;
        private String message;
    }
}
