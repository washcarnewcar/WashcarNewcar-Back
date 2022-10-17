package me.washcar.wcnc.dto;

import lombok.Getter;

public class VehicleData {
    //TODO 자동차브랜드리스트-Dto
    @Getter
    public static class brandListDto {
        private Object brand;
    }

    //TODO 자동차모델리스트-Dto
    @Getter
    public static class modelListDto {
        private Object model;
    }
}
