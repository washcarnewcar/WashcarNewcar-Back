package me.washcar.wcnc.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.StoreInfo;
import me.washcar.wcnc.dto.StoreInfo.StoreInfoDto;
import me.washcar.wcnc.dto.StoreInfo.StoreMenuDto;
import me.washcar.wcnc.dto.StoreInfo.StoreDetailDto;
import me.washcar.wcnc.dto.provider.StoreCreate.StoreAllInfoDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import me.washcar.wcnc.entity.StoreMenu;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.exception.ErrorCode;
import me.washcar.wcnc.repository.StoreImageRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreInfoService {

  private final StoreRepository storeRepository;

  //TODO 세차장리스트조회-서비스
  public StoreInfo.searchStoreByLocationDto searchStoreByLocation(float longitude, float latitude) {
    return new StoreInfo.searchStoreByLocationDto();
  }

  // 세차장 정보
  public StoreAllInfoDto getStoreInfo(String slug) {
    // TODO : 승인되지 않은 세차장에 대해서는 조회되지 않도록 처리
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    return StoreAllInfoDto.from(store);
  }

  // 세차장 메뉴 조회
  public List<StoreMenuDto> getStoreMenuList(String slug) {
    // TODO : 승인되지 않은 세차장에 대해서는 조회되지 않도록 처리
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    List<StoreMenu> storeMenus = store.getStoreMenus();
    return storeMenus.stream().map(StoreMenuDto::from).collect(Collectors.toList());
  }

  //  세차장세부정보-서비스
  public StoreDetailDto getStoreDetail(String slug) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    return StoreDetailDto.from(store);
  }
}
