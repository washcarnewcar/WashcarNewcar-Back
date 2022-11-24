package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.StoreInfo.MenuListResult;
import me.washcar.wcnc.dto.provider.StoreMenuDto.DeleteDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuRequestDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuResponseDto;
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
      @Validated @RequestBody MenuRequestDto menuRequestDto
  ) {
    return SuccessCreateDto.getSuccessCreateDto(storeMenuService.createStoreMenu(slug,
        menuRequestDto));
  }

  @PutMapping("/provider/menu/{menuId}")
  public UpdateDto updateStoreMenu(
      @PathVariable Long menuId,
      @RequestBody MenuRequestDto menuRequestDto
  ) {
    return storeMenuService.updateStoreMenu(menuId, menuRequestDto);
  }

  @DeleteMapping("/provider/menu/{menuId}")
  public DeleteDto deleteStoreMenu(
      @PathVariable Long menuId
  ) {
    return storeMenuService.deleteStoreMenu(menuId);
  }

  @GetMapping("/provider/{slug}/menu")
  public MenuListResult<MenuResponseDto> getMenuList(@PathVariable String slug) {
    return MenuListResult.getMenuListResult(storeMenuService.getMenuList(slug));
  }

  @GetMapping("/provider/menu/{menuId}")
  public MenuResponseDto getMenu(@PathVariable Long menuId) {
    return storeMenuService.getMenu(menuId);
  }
}

