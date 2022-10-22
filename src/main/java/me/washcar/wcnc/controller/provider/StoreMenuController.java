package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.StoreMenu;
import me.washcar.wcnc.service.provider.StoreMenuService;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequiredArgsConstructor
public class StoreMenuController {

    private final StoreMenuService storeMenuService;

    @PostMapping("/provider/{slug}/menu/write")
    public StoreMenu.createDto create(
            @PathVariable String slug,
            @RequestBody String body
    ) {
        return storeMenuService.create(slug, body);
    }

    @PatchMapping("/provider/{slug}/menu/{menuNumber}")
    public StoreMenu.updateDto update(
            @PathVariable String slug,
            @PathVariable long menuNumber,
            @RequestBody String body
    ) {
        return storeMenuService.update(slug, menuNumber, body);
    }

    @DeleteMapping("/provider/{slug}/menu/{menuNumber}")
    public StoreMenu.updateDto delete(
            @PathVariable String slug,
            @PathVariable long menuNumber
    ) {
        return storeMenuService.delete(slug, menuNumber);
    }
}

