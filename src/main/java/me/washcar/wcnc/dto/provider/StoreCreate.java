package me.washcar.wcnc.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StoreCreate {

    //TODO 세차장승인상태확인-Dto
    @Getter
    @AllArgsConstructor
    public static class isApprovedDto {
        private int status;
        private String message;
        private String reason;
    }
}
