package me.washcar.wcnc.dto;

import lombok.Getter;

public class Reservation {
    //TODO 메뉴에대한정보-Dto
    @Getter
    public static class menuInfoDto {
        private String image;
        private String name;
        private String detail;
        private int price;
        private Object options;
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
