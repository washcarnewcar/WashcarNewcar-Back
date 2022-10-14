package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.StoreInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StoreInfoService {
    //TODO 세차장리스트조회-서비스
    public StoreInfoDto.searchStoreByLocation searchStoreByLocation(float longitude, float latitude){
        return new StoreInfoDto.searchStoreByLocation();
    }
    //TODO 세차장정보-서비스
    public StoreInfoDto.storeInfo storeInfo(String slug) {
        return new StoreInfoDto.storeInfo();
    }
    //TODO 세차장메뉴-서비스
    public StoreInfoDto.storeMenu storeMenu(String slug) {
        return new StoreInfoDto.storeMenu();
    }
    //TODO 세차장세부정보-서비스
    public StoreInfoDto.storeDetail storeDetail(String slug) {
        return new StoreInfoDto.storeDetail();
    }
}
