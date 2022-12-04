package me.washcar.wcnc.service.provider;

import static me.washcar.wcnc.exception.ErrorCode.STORE_IMAGE_NOT_FOUND;
import static me.washcar.wcnc.exception.ErrorCode.STORE_LOCATION_NOT_FOUND;
import static me.washcar.wcnc.exception.ErrorCode.STORE_NOT_FOUND;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto._StatusCodeDto;
import me.washcar.wcnc.dto.provider.StoreCreate;
import me.washcar.wcnc.dto.provider.StoreCreate.StoreAllInfoDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import me.washcar.wcnc.entity.StoreLocation;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.form.NewStoreCreationForm;
import me.washcar.wcnc.repository.StoreImageRepository;
import me.washcar.wcnc.repository.StoreLocationRepository;
import me.washcar.wcnc.repository.StoreRepository;
import me.washcar.wcnc.repository.UserRepository;
import me.washcar.wcnc.service._UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor
public class StoreCreateService {

  private final StoreRepository storeRepository;

  private final StoreLocationRepository storeLocationRepository;

  private final StoreImageRepository storeImageRepository;

  private final UserRepository userRepository;

  private final _UserService userService;

  @Transactional
  public _StatusCodeDto create(NewStoreCreationForm form) {
    if (storeRepository.findBySlug(form.getSlug()).orElse(null) != null) {
      return new _StatusCodeDto(1302, "slug 중복됨");
    }

    StoreLocation storeLocation = generateStoreLocation(form);
    Store store = generateStore(form, storeLocation);
    storeLocation.setStore(store);
    storeLocationRepository.save(storeLocation);

    List<String> imageUrls = form.getStore_image().stream().toList();
    for (String imageUrl : imageUrls) {
      storeImageRepository.save(StoreImage.builder().imageUrl(imageUrl).store(store).build());
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    addStoreToUser(authentication.getPrincipal().toString(), form.getSlug());

    return new _StatusCodeDto(1300, "매장 승인 요청 성공");
  }

  public void addStoreToUser(String email, String slug) {
    User user = userRepository.findByEmail(email);
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));
    user.getStores().add(store);
    userRepository.save(user);
  }

  StoreLocation generateStoreLocation(NewStoreCreationForm form) {
    return storeLocationRepository.save(StoreLocation.builder()
        .address(form.getAddress())
        .latitude(form.getCoordinate().getLatitude())
        .longitude(form.getCoordinate().getLongitude())
        .build());
  }

  Store generateStore(NewStoreCreationForm form, StoreLocation storeLocation) {
    return storeRepository.save(Store.builder()
        .name(form.getName())
        .tel(form.getTel())
        .address(form.getAddress())
        .addressDetail(form.getAddress_detail())
        .slug(form.getSlug())
        .wayTo(form.getWayto())
        .description(form.getDescription())
        .previewImage(form.getPreview_image())
        .isChecked(false)
        .isApproved(false)
        .storeLocation(storeLocation)
        .build());
  }

  public _StatusCodeDto update(NewStoreCreationForm form, String slug) {
    Store store = storeRepository.findBySlug(slug).orElseThrow(
        () -> new CustomException(STORE_NOT_FOUND)
    );

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUser(authentication.getPrincipal().toString());

    if (storeRepository.findBySlug(slug).orElse(null) != null) {
      if (!user.getStores().isEmpty()) {
        Set<Store> userStore = user.getStores();
        for (Store store1 : userStore) {
          if (!Objects.equals(slug, store1.getSlug())) {
            return new _StatusCodeDto(2502, "slug 중복됨");
          }
        }
      }
    }

    StoreLocation storeLocation = storeLocationRepository.findByStore(store)
        .orElseThrow(() -> new CustomException(STORE_LOCATION_NOT_FOUND));
    storeLocation.setStoreLocation(form, store);
    storeLocationRepository.save(storeLocation);

    store.setStore(form, storeLocation);
    storeRepository.save(store);

    List<StoreImage> storeImages = storeImageRepository.findByStore_StoreId(store.getStoreId())
        .orElseThrow(() -> new CustomException(STORE_IMAGE_NOT_FOUND));
    storeImageRepository.deleteAll(storeImages);

    List<String> imageUrls = form.getStore_image().stream().toList();
    for (String imageUrl : imageUrls) {
      storeImageRepository.save(StoreImage.builder().imageUrl(imageUrl).store(store).build());
    }

    return new _StatusCodeDto(2500, "세차장 정보 수정 완료");
  }

  public _StatusCodeDto slugCheck(String slug) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUser(authentication.getPrincipal().toString());

    if (!user.getStores().isEmpty()) {
      Store userStore = user.getStores().iterator().next();
      if (Objects.equals(slug, userStore.getSlug())) {
        return new _StatusCodeDto(1400, "사용 가능한 slug");
      }
    }

    if (storeRepository.findBySlug(slug).orElse(null) != null) {
      return new _StatusCodeDto(1401, "중복된 slug");
    } else {
      return new _StatusCodeDto(1400, "사용 가능한 slug");
    }
  }

  public StoreCreate.isApprovedDto isApproved(String slug) {
    Store store = storeRepository.findBySlug(slug).orElseThrow(
        () -> new CustomException(STORE_NOT_FOUND)
    );
    if (!store.getIsChecked()) {
      return new StoreCreate.isApprovedDto(1501, "세차장 승인 대기중", "");
    }
    if (store.getIsApproved()) {
      return new StoreCreate.isApprovedDto(1500, "세차장이 승인되었으며, 페이지가 운영중", "");
    }
    return new StoreCreate.isApprovedDto(1502, "세차장 승인 거부", "");
  }

  public StoreCreate.getSlugDto getSlug() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.getUser(authentication.getPrincipal().toString());

    if (user.getStores().isEmpty()) {
      return new StoreCreate.getSlugDto(2601, "세차장 등록하지 않음", "null");
    } else {
      Store userStore = user.getStores().iterator().next();
      return new StoreCreate.getSlugDto(2600, "세차장 등록함", userStore.getSlug());
    }
  }

  public StoreAllInfoDto getStoreInfo(String slug) {
    Store store = storeRepository.findBySlug(slug).orElseThrow(
        () -> new CustomException(STORE_NOT_FOUND)
    );
    return StoreAllInfoDto.from(store);
  }
}
