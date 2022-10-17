package me.washcar.wcnc.dto;

import lombok.Getter;

import java.util.List;

public class StoreInfo {
    //TODO 세차장리스트조회-Dto
    @Getter
    public static class searchStoreByLocationDto {
        private List<String> store;
    }
    //TODO 세차장정보-Dto
    @Getter
    public static class storeInfoDto {
        private String store;
    }
    //TODO 세차장메뉴-Dto
    @Getter
    public static class storeMenuDto {
        private String store;
    }
    //TODO 세차장세부정보-Dto
    @Getter
    public static class storeDetailDto {
        private String detail;
    }
}
