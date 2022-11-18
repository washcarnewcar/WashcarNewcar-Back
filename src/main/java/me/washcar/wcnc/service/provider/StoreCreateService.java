package me.washcar.wcnc.service.provider;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto._StatusCodeDto;
import me.washcar.wcnc.dto.provider.StoreCreate;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import me.washcar.wcnc.entity.StoreLocation;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.form.NewStoreCreationForm;
import me.washcar.wcnc.repository.StoreImageRepo;
import me.washcar.wcnc.repository.StoreLocationRepo;
import me.washcar.wcnc.repository.StoreRepository;
import me.washcar.wcnc.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class StoreCreateService {

  @Autowired
  StoreRepository storeRepository;
  @Autowired
  StoreLocationRepo storeLocationRepo;
  @Autowired
  StoreImageRepo storeImageRepo;
  @Autowired
  UserRepo userRepo;

  public void addStoreToUser(String email, String slug) {
    log.info("Adding store {} to user {}", slug, email);
    User user = userRepo.findByEmail(email);
    Store store = storeRepository.findBySlug(slug).orElse(null);
    user.getStores().add(store);
    userRepo.save(user);
  }

  public _StatusCodeDto request(NewStoreCreationForm form) {

      if (storeRepository.findBySlug(form.getSlug()).orElse(null) != null) {
          return new _StatusCodeDto(1302, "slug 중복됨");
      }

    Store store = Store.builder()
        .name(form.getName())
        .tel(form.getTel())
        .slug(form.getSlug())
        .wayTo(form.getWayto())
        .description(form.getDescription())
        .previewImage(form.getPreview_image())
        .isChecked(false)
        .isApproved(false)
        .build();
    storeRepository.save(store);

    StoreLocation storeLocation = StoreLocation.builder()
        .address(form.getAddress())
        .latitude(form.getCoordinate().getLatitude())
        .longitude(form.getCoordinate().getLongitude())
        .store(store)
        .build();
    storeLocationRepo.save(storeLocation);

    Collection<String> images = form.getStore_image();
    images.forEach(
        image -> storeImageRepo.save(StoreImage.builder().imageUrl(image).store(store).build()));

    return new _StatusCodeDto(1300, "매장 승인 요청 성공");
  }

  public _StatusCodeDto update(NewStoreCreationForm form, String slug) {

    Store store = storeRepository.findBySlug(slug).orElse(null);
    if (store != null) {

      if (!Objects.equals(slug, form.getSlug())) {
          if (storeRepository.findBySlug(form.getSlug()) != null) {
              return new _StatusCodeDto(2502, "slug 중복됨");
          }
      }

      store.setName(form.getName());
      store.setTel(form.getTel());
      store.setSlug(form.getSlug());
      store.setWayTo(form.getWayto());
      store.setDescription(form.getDescription());
      store.setPreviewImage(form.getPreview_image());
      storeRepository.save(store);

      StoreLocation storeLocation = storeLocationRepo.findByStore(store);
      storeLocation.setAddress(form.getAddress());
      storeLocation.setLatitude(form.getCoordinate().getLatitude());
      storeLocation.setLongitude(form.getCoordinate().getLongitude());
      storeLocationRepo.save(storeLocation);

      Collection<StoreImage> storeImages = storeImageRepo.findByStore(store);
      storeImages.forEach(storeImage -> storeImageRepo.delete(storeImage));

      Collection<String> imageStrings = form.getStore_image();
      imageStrings.forEach(imageString -> storeImageRepo.save(
          StoreImage.builder().imageUrl(imageString).store(store).build()));

      return new _StatusCodeDto(2500, "세차장 정보 수정 완료");
    } else {
      return new _StatusCodeDto(-1, "세차장이 존재하지 않음");
    }
  }

  public _StatusCodeDto slugCheck(String slug) {
    if (storeRepository.findBySlug(slug) != null) {
      return new _StatusCodeDto(1401, "중복된 slug");
    } else {
      return new _StatusCodeDto(1400, "사용 가능한 slug");
    }
  }

  public StoreCreate.isApprovedDto isApproved(String slug) {
    Store store = storeRepository.findBySlug(slug).orElse(null);
      if (store == null) {
          return new StoreCreate.isApprovedDto(-1, "error", "세차장이 존재하지 않음");
      }
      if (!store.getIsChecked()) {
          return new StoreCreate.isApprovedDto(1501, "세차장 승인 대기중", "");
      }
      if (store.getIsApproved()) {
          return new StoreCreate.isApprovedDto(1500, "세차장이 승인되었으며, 페이지가 운영중", "");
      }
    return new StoreCreate.isApprovedDto(1502, "세차장 승인 거부", "");
  }
}
