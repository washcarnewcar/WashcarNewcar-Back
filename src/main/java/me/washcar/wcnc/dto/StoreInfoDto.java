package me.washcar.wcnc.dto;

import lombok.Getter;

import java.util.List;

public class StoreInfoDto {
    //TODO 세차장리스트조회-Dto
    @Getter
    public static class searchStoreByLocation {
        private List<String> store;
    }
    //TODO 세차장정보-Dto
    @Getter
    public static class storeInfo {
        private String store;
    }
    //TODO 세차장메뉴-Dto
    @Getter
    public static class storeMenu {
        private String store;
    }
    //TODO 세차장세부정보-Dto
    @Getter
    public static class storeDetail {
        private String detail;
    }
}
