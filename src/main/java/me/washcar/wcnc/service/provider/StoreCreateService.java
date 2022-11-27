package me.washcar.wcnc.service.provider;

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
import me.washcar.wcnc.exception.ErrorCode;
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

  @Autowired
  private final StoreRepository storeRepository;
  @Autowired
  private final StoreLocationRepository storeLocationRepository;
  @Autowired
  private final StoreImageRepository storeImageRepository;
  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final _UserService userService;

  public void addStoreToUser(String email, String slug) {
    log.info("Adding store {} to user {}", slug, email);
    User user = userRepository.findByEmail(email);
    Store store = storeRepository.findBySlug(slug).orElse(null);
    user.getStores().add(store);
    userRepository.save(user);
  }

  public _StatusCodeDto request(NewStoreCreationForm form) {

    if (storeRepository.findBySlug(form.getSlug()).orElse(null) != null) {   //TODO: 구문 단순화 필요
      return new _StatusCodeDto(1302, "slug 중복됨");
    }

    StoreLocation storeLocation = StoreLocation.builder()
        .address(form.getAddress())
        .latitude(form.getCoordinate().getLatitude())
        .longitude(form.getCoordinate().getLongitude())
        .build();
    storeLocationRepository.save(storeLocation);

    Store store = Store.builder()
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
        .build();
    storeRepository.save(store);

    Collection<String> images = form.getStore_image();
    images.forEach(
        image -> storeImageRepository.save(
            StoreImage.builder().imageUrl(image).store(store).build()));

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    addStoreToUser(authentication.getPrincipal().toString(), form.getSlug());

    return new _StatusCodeDto(1300, "매장 승인 요청 성공");
  }

  public _StatusCodeDto update(NewStoreCreationForm form, String slug) {

    Store store = storeRepository.findBySlug(slug).orElseThrow(
      ()-> new CustomException(ErrorCode.STORE_NOT_FOUND)
    );

    if (!Objects.equals(slug, form.getSlug())) {
      if (storeRepository.findBySlug(form.getSlug()).orElse(null) != null) {
        return new _StatusCodeDto(2502, "slug 중복됨");
      }
    }

    StoreLocation storeLocation = store.getStoreLocation();
    storeLocation.setAddress(form.getAddress());
    storeLocation.setLatitude(form.getCoordinate().getLatitude());
    storeLocation.setLongitude(form.getCoordinate().getLongitude());

    store.setName(form.getName());
    store.setTel(form.getTel());
    store.setAddress(form.getAddress());
    store.setAddressDetail(form.getAddress_detail());
    store.setSlug(form.getSlug());
    store.setWayTo(form.getWayto());
    store.setDescription(form.getDescription());
    store.setPreviewImage(form.getPreview_image());
    store.setStoreLocation(storeLocation);
    storeRepository.save(store);

    Collection<StoreImage> storeImages = store.getStoreImages();
    storeImageRepository.deleteAll(storeImages);

    Collection<String> imageStrings = form.getStore_image();
    imageStrings.forEach(imageString -> storeImageRepository.save(
        StoreImage.builder().imageUrl(imageString).store(store).build())
    );

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
      ()-> new CustomException(ErrorCode.STORE_NOT_FOUND)
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
            ()-> new CustomException(ErrorCode.STORE_NOT_FOUND)
    );
    return StoreAllInfoDto.from(store);
  }
}
