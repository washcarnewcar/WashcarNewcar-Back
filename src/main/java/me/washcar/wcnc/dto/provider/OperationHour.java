package me.washcar.wcnc.dto.provider;

import lombok.Getter;

public class OperationHour {
    //TODO 운영시간설정-Dto
    @Getter
    public static class settingDto {
        private int status;
        private String message;
    }

    //TODO 운영시간예외설정-Dto
    @Getter
    public static class exceptionSettingDto {
        private int status;
        private String message;
    }
}
