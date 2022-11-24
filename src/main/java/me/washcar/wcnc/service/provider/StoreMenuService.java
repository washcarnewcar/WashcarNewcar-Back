package me.washcar.wcnc.service.provider;

import static me.washcar.wcnc.dto.provider.StoreMenuDto.DeleteDto.getDeleteDto;
import static me.washcar.wcnc.dto.provider.StoreMenuDto.UpdateDto.getUpdateDto;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.StoreMenuDto.DeleteDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.UpdateDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreMenu;
import me.washcar.wcnc.repository.StoreMenuRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

  private final StoreRepository storeRepository;

  private final StoreMenuRepository storeMenuRepository;

  public Long createStoreMenu(String slug, MenuDto menuDto) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null);
    return storeMenuRepository.save(generateStoreMenu(store, menuDto)).getStoreMenuId();
  }

  private StoreMenu generateStoreMenu(Store store, MenuDto menuDto) {
    return StoreMenu.builder()
        .image(menuDto.getImage())
        .name(menuDto.getName())
        .description(menuDto.getDescription())
        .price(menuDto.getPrice())
        .expected_time(menuDto.getExpected_time())
        .store(store)
        .build();
  }

  public UpdateDto updateStoreMenu(String slug, Long menuId, MenuDto menuDto) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null); // 없어도 구현 가능..
    // TODO: 해당 id의 storeOption을 찾을 수 없으면 Custom Exception을 던져야 함.
    StoreMenu storeMenu = storeMenuRepository.findById(menuId).orElse(null);
    storeMenu.updateStoreMenu(menuDto);
    storeMenuRepository.save(storeMenu);
    return getUpdateDto();
  }

  public DeleteDto deleteStoreMenu(String slug, Long menuId) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null); // 없어도 구현 가능..
    StoreMenu storeMenu = storeMenuRepository.findById(menuId).orElse(null);
    storeMenuRepository.delete(storeMenu);
    return getDeleteDto();
  }
}
