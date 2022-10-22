package me.washcar.wcnc.service.provider;

import me.washcar.wcnc.dto.provider.StoreMenu;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class StoreMenuService {

    //TODO 메뉴추가-서비스
    public StoreMenu.createDto create(String slug, String body) {
        //TODO body-mapping
        return new StoreMenu.createDto();
    }

    //TODO 메뉴업데이트-서비스
    public StoreMenu.updateDto update(String slug, long menuNumber, String body) {
        //TODO body-mapping
        return new StoreMenu.updateDto();
    }

    public StoreMenu.updateDto delete(String slug, long menuNumber) {
        return new StoreMenu.updateDto();
    }

    //TODO 메뉴삭제-서비스 (?)

    //TODO 메뉴읽기-서비스 (?)
}
