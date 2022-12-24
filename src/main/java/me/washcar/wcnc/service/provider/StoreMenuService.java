package me.washcar.wcnc.service.provider;

import static java.util.stream.Collectors.toList;
import static me.washcar.wcnc.dto.provider.StoreMenuDto.DeleteDto.getDeleteDto;
import static me.washcar.wcnc.dto.provider.StoreMenuDto.UpdateDto.getUpdateDto;
import static me.washcar.wcnc.exception.ErrorCode.STORE_MENU_NOT_FOUND;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.StoreMenuDto.DeleteDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuRequestDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuResponseDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.UpdateDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreMenu;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.exception.ErrorCode;
import me.washcar.wcnc.repository.StoreMenuRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

  private final StoreRepository storeRepository;

  private final StoreMenuRepository storeMenuRepository;

  public Long createStoreMenu(String slug, MenuRequestDto menuRequestDto) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    return storeMenuRepository.save(generateStoreMenu(store, menuRequestDto)).getId();
  }

  private StoreMenu generateStoreMenu(Store store, MenuRequestDto menuRequestDto) {
    return StoreMenu.builder()
        .image(menuRequestDto.getImage())
        .name(menuRequestDto.getName())
        .description(menuRequestDto.getDescription())
        .price(menuRequestDto.getPrice())
        .expected_hour(menuRequestDto.getExpected_hour())
        .expected_minute(menuRequestDto.getExpected_minute())
        .store(store)
        .build();
  }

  public UpdateDto updateStoreMenu(Long menuId, MenuRequestDto menuRequestDto) {
    StoreMenu storeMenu = storeMenuRepository.findById(menuId)
        .orElseThrow(() -> new CustomException(STORE_MENU_NOT_FOUND));
    storeMenu.updateStoreMenu(menuRequestDto);
    storeMenuRepository.save(storeMenu);
    return getUpdateDto();
  }

  public DeleteDto deleteStoreMenu(Long menuId) {
    StoreMenu storeMenu = storeMenuRepository.findById(menuId).orElse(null);
    storeMenuRepository.delete(storeMenu);
    return getDeleteDto();
  }

  public List<MenuResponseDto> getMenuList(String slug) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    List<StoreMenu> storeMenus = store.getStoreMenus();
    return storeMenus.stream().map(MenuResponseDto::from).collect(toList());
  }

  public MenuResponseDto getMenu(Long menuId) {
    StoreMenu storeMenu = storeMenuRepository.findById(menuId)
        .orElseThrow(() -> new CustomException(STORE_MENU_NOT_FOUND));
    return MenuResponseDto.from(storeMenu);
  }
}
