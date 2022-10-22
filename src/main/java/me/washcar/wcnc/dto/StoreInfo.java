package me.washcar.wcnc.dto;

import lombok.Getter;

import java.util.List;

public class StoreInfo {
    //TODO 세차장리스트조회-Dto
    @Getter
    public static class searchStoreByLocationDto {
        private List<Object> store;
    }
    //TODO 세차장정보-Dto
    @Getter
    public static class storeInfoDto {
        private String preview_image;
        private List<String> images;
        private String name;
    }
    //TODO 세차장메뉴-Dto
    @Getter
    public static class storeMenuDto {
        private List<Object> menu;
    }
    //TODO 세차장세부정보-Dto
    @Getter
    public static class storeDetailDto {
        private String phone;
        private String address;
        private String wayto;
        private String info;
    }
}
