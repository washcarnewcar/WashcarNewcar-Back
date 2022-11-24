package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.StoreMenuDto.DeleteDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.SuccessCreateDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.UpdateDto;
import me.washcar.wcnc.service.provider.StoreMenuService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreMenuController {

  private final StoreMenuService storeMenuService;

  @PostMapping("/provider/{slug}/menu")
  public SuccessCreateDto createStoreMenu(
      @PathVariable String slug,
      @Validated @RequestBody MenuDto menuDto
  ) {
    return SuccessCreateDto.getSuccessCreateDto(storeMenuService.createStoreMenu(slug, menuDto));
  }

  @PutMapping("/provider/{slug}/menu/{menuId}")
  public UpdateDto updateStoreMenu(
      @PathVariable String slug,
      @PathVariable Long menuId,
      @RequestBody MenuDto menuDto
  ) {
    return storeMenuService.updateStoreMenu(slug, menuId, menuDto);
  }

  @DeleteMapping("/provider/{slug}/menu/{menuId}")
  public DeleteDto deleteStoreMenu(
      @PathVariable String slug,
      @PathVariable Long menuId
  ) {
    return storeMenuService.deleteStoreMenu(slug, menuId);
  }
}

