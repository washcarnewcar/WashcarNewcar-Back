package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.StoreInfo;
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
    public StoreInfo.searchStoreByLocationDto searchStoreByLocation(@RequestParam("longitude") float longitude, @RequestParam("latitude") float latitude){
        return storeInfoService.searchStoreByLocation(longitude, latitude);
    }

    @GetMapping("/store/{slug}/info")
    public StoreInfo.storeInfoDto storeInfo(@PathVariable String slug) {
        return storeInfoService.storeInfo(slug);
    }

    @GetMapping("/store/{slug}/menu")
    public StoreInfo.storeMenuDto storeMenu(@PathVariable String slug) {
        return storeInfoService.storeMenu(slug);
    }

    @GetMapping("/store/{slug}/detail")
    public StoreInfo.storeDetailDto storeDetail(@PathVariable String slug) {
        return storeInfoService.storeDetail(slug);
    }
}
