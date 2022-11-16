package me.washcar.wcnc.controller.provider;

import javax.validation.Valid;
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
      @Valid @RequestBody MenuDto menuDto
  ) {
    return SuccessCreateDto.getSuccessCreateDto(storeMenuService.createStoreMenu(slug, menuDto));
  }

  @PutMapping("/provider/{slug}/menu/{storeOptionId}")
  public UpdateDto updateStoreMenu(
      @PathVariable String slug,
      @PathVariable Long storeOptionId,
      @RequestBody MenuDto menuDto
  ) {
    return storeMenuService.updateStoreMenu(slug, storeOptionId, menuDto);
  }

  @DeleteMapping("/provider/{slug}/menu/{storeOptionId}")
  public DeleteDto delete(
      @PathVariable String slug,
      @PathVariable Long storeOptionId
  ) {
    return storeMenuService.delete(slug, storeOptionId);
  }
}

