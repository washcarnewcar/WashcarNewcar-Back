package me.washcar.wcnc.dto.provider;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class StoreCreate {

    @Getter
    @AllArgsConstructor
    public static class isApprovedDto {
        private int status;
        private String message;
        private String reason;
    }

    @Getter
    @AllArgsConstructor
    public static class getSlugDto {
        private int status;
        private String message;
        private String slug;
    }
}
