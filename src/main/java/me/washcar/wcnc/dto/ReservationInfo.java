package me.washcar.wcnc.dto;

import lombok.Getter;

import java.time.ZonedDateTime;

public class ReservationInfo {
    //TODO 휴대폰번호로예약리스트조회-Dto
    @Getter
    public static class checkWithPhoneDto {
        private Object list;
    }
    //TODO 예약한세차상세조회-Dto
    @Getter
    public static class checkWithReservationNumberDto {
        private String status;
        private String car_name;
        private String car_number;
        private String store_name;
        private String store_slug;
        private String menu;
        private int price;
        private ZonedDateTime date;
        private ZonedDateTime expected_wash_time;
        private Object option;
    }
}
