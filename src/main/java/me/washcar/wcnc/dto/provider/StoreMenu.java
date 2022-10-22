package me.washcar.wcnc.dto.provider;

import lombok.Getter;

public class StoreMenu {

    //TODO 메뉴추가-Dto
    @Getter
    public static class createDto {
        private int status;
        private String message;
    }

    //TODO 메뉴업데이트-Dto
    @Getter
    public static class updateDto {
        private int status;
        private String message;
    }

    //TODO 메뉴삭제-Dto (?)

    //TODO 메뉴읽기-Dto (?)
}
