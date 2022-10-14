package me.washcar.wcnc.dto;

import lombok.Getter;

public class ReservationDto {
    //TODO 메뉴에대한정보-Dto
    @Getter
    public static class menuInfo {
        private String image;
        private String name;
        private String detail;
        private int price;
        private Object options;
    }
    //TODO 가능한날짜리스트-Dto
    @Getter
    public static class menuAvailableDate {
        private Object available_date;
    }
    //TODO 날짜별예약가능한시간리스트-Dto
    @Getter
    public static class menuAvailableTime {
        private Object available_time;
    }
    //TODO 세차예약요청-Dto
    @Getter
    public static class reservation {
        private int status;
        private String message;
        private Object reservation_info;
    }
}
