package me.washcar.wcnc.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.StoreInfo;
import me.washcar.wcnc.dto.StoreInfo.StoreInfoDto;
import me.washcar.wcnc.dto.StoreInfo.StoreMenuDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreOption;
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

  //TODO 세차장정보-서비스
  public StoreInfoDto getStoreInfo(String slug) {
    return new StoreInfoDto();
  }

  public List<StoreMenuDto> getStoreMenu(String slug) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null);
    List<StoreOption> storeOptions = store.getStoreOptions();
    return storeOptions.stream().map(StoreMenuDto::from).collect(Collectors.toList());
  }

  //TODO 세차장세부정보-서비스
  public StoreInfo.storeDetailDto storeDetail(String slug) {
    return new StoreInfo.storeDetailDto();
  }
}
