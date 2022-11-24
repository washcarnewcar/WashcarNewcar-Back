package me.washcar.wcnc.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.StoreInfo;
import me.washcar.wcnc.dto.StoreInfo.StoreInfoDto;
import me.washcar.wcnc.dto.StoreInfo.StoreMenuDto;
import me.washcar.wcnc.dto.StoreInfo.StoreDetailDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import me.washcar.wcnc.entity.StoreMenu;
import me.washcar.wcnc.repository.StoreImageRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreInfoService {

  private final StoreRepository storeRepository;

  private final StoreImageRepository storeImageRepository;

  //TODO 세차장리스트조회-서비스
  public StoreInfo.searchStoreByLocationDto searchStoreByLocation(float longitude, float latitude) {
    return new StoreInfo.searchStoreByLocationDto();
  }

  // 세차장 정보
  public StoreInfoDto getStoreInfo(String slug) {
    // TODO: 해당 slug 의 store 을 찾을 수 없으면 Custom Exception 을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null);
    // TODO: Store 에 등록된 StoreImage 이미지가 없다면, 빈 객체를 반환해야함.
    List<StoreImage> storeImages = storeImageRepository.findByStore_StoreId(store.getStoreId())
        .orElse(null);
    return StoreInfoDto.from(store, storeImages);
  }

  // 세차장 메뉴 조회
  public List<StoreMenuDto> getStoreMenuList(String slug) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null);
    List<StoreMenu> storeMenus = store.getStoreMenus();
    return storeMenus.stream().map(StoreMenuDto::from).collect(Collectors.toList());
  }

  //  세차장세부정보-서비스
  public StoreDetailDto getStoreDetail(String slug) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null);
    return StoreDetailDto.from(store);
  }
}
