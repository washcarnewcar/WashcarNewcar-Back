package me.washcar.wcnc.service.provider;

import static me.washcar.wcnc.dto.provider.StoreMenuDto.DeleteDto.getDeleteDto;
import static me.washcar.wcnc.dto.provider.StoreMenuDto.UpdateDto.getUpdateDto;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.StoreMenuDto.DeleteDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.MenuDto;
import me.washcar.wcnc.dto.provider.StoreMenuDto.UpdateDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreOption;
import me.washcar.wcnc.repository.StoreOptionRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

  private final StoreRepository storeRepository;

  private final StoreOptionRepository storeOptionRepository;

  public Long createStoreMenu(String slug, MenuDto menuDto) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null);
    return storeOptionRepository.save(generateStoreOption(store, menuDto)).getStoreOptionId();
  }

  private StoreOption generateStoreOption(Store store, MenuDto menuDto) {
    return StoreOption.builder()
        .image(menuDto.getImage())
        .name(menuDto.getName())
        .description(menuDto.getDescription())
        .price(menuDto.getPrice())
        .expected_time(menuDto.getExpected_time())
        .store(store)
        .build();
  }

  public UpdateDto updateStoreMenu(String slug, Long storeOptionId, MenuDto menuDto) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null); // 없어도 구현 가능..
    // TODO: 해당 id의 storeOption을 찾을 수 없으면 Custom Exception을 던져야 함.
    StoreOption storeOption = storeOptionRepository.findById(storeOptionId).orElse(null);
    storeOption.updateStoreOption(menuDto);
    storeOptionRepository.save(storeOption);
    return getUpdateDto();
  }

  public DeleteDto delete(String slug, Long storeOptionId) {
    // TODO: 해당 slug의 store을 찾을 수 없으면 Custom Exception을 던져야 함.
    Store store = storeRepository.findBySlug(slug).orElse(null); // 없어도 구현 가능..
    StoreOption storeOption = storeOptionRepository.findById(storeOptionId).orElse(null);
    storeOptionRepository.delete(storeOption);
    return getDeleteDto();
  }
}
