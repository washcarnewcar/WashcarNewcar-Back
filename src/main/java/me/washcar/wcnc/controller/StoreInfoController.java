package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.StoreInfo;
import me.washcar.wcnc.dto.StoreInfo.MenuListResult;
import me.washcar.wcnc.dto.StoreInfo.StoreInfoDto;
import me.washcar.wcnc.dto.StoreInfo.StoreMenuDto;
import me.washcar.wcnc.dto.StoreInfo.StoreDetailDto;
import me.washcar.wcnc.service.StoreInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreInfoController {

  private final StoreInfoService storeInfoService;

  @GetMapping("/search/store")
  public StoreInfo.searchStoreByLocationDto searchStoreByLocation(
      @RequestParam("longitude") float longitude, @RequestParam("latitude") float latitude) {
    return storeInfoService.searchStoreByLocation(longitude, latitude);
  }

  @GetMapping("/store/{slug}/info")
  public StoreInfoDto getStoreInfo(@PathVariable String slug) {
    return storeInfoService.getStoreInfo(slug);
  }

  @GetMapping("/store/{slug}/menu")
  public MenuListResult<StoreMenuDto> storeMenu(@PathVariable String slug) {
    return MenuListResult.getMenuListResult(storeInfoService.getStoreMenuList(slug));
  }

  @GetMapping("/store/{slug}/detail")
  public StoreDetailDto getStoreDetail(@PathVariable String slug) {
    return storeInfoService.getStoreDetail(slug);
  }
}
