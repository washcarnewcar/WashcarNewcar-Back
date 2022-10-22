package me.washcar.wcnc.dto.provider;

import lombok.Getter;

import java.time.ZonedDateTime;

public class Schedule {

    //TODO 세차스케줄리스트-Dto
    @Getter
    public static class listDto {
        private Object list;
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
