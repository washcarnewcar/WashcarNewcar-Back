package me.washcar.wcnc.dto.provider;

import lombok.Getter;

public class StoreCreate {

    //TODO slug중복확인-Dto
    @Getter
    public static class slugCheckDto {
        private int status;
        private String message;
    }

    //TODO 세차장승인상태확인-Dto
    @Getter
    public static class isApprovedDto {
        private int status;
        private String message;
        private String reason;
    }
}
