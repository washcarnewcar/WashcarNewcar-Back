package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.StoreInfo;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StoreInfoService {
    //TODO 세차장리스트조회-서비스
    public StoreInfo.searchStoreByLocationDto searchStoreByLocation(float longitude, float latitude){
        return new StoreInfo.searchStoreByLocationDto();
    }
    //TODO 세차장정보-서비스
    public StoreInfo.storeInfoDto storeInfo(String slug) {
        return new StoreInfo.storeInfoDto();
    }
    //TODO 세차장메뉴-서비스
    public StoreInfo.storeMenuDto storeMenu(String slug) {
        return new StoreInfo.storeMenuDto();
    }
    //TODO 세차장세부정보-서비스
    public StoreInfo.storeDetailDto storeDetail(String slug) {
        return new StoreInfo.storeDetailDto();
    }
}
