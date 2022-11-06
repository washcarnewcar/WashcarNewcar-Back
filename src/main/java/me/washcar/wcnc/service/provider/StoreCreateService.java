package me.washcar.wcnc.service.provider;

import me.washcar.wcnc.dto.StatusCodeDto;
import me.washcar.wcnc.dto.provider.StoreCreate;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreImage;
import me.washcar.wcnc.entity.StoreLocation;
import me.washcar.wcnc.form.NewStoreCreationForm;
import me.washcar.wcnc.repository.StoreImageRepo;
import me.washcar.wcnc.repository.StoreLocationRepo;
import me.washcar.wcnc.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StoreCreateService {

    @Autowired
    StoreRepo storeRepo;
    @Autowired
    StoreLocationRepo storeLocationRepo;

    @Autowired
    StoreImageRepo storeImageRepo;

    public StatusCodeDto request(NewStoreCreationForm form) {

        if (storeRepo.findBySlug(form.getSlug()) != null) {
            return new StatusCodeDto(1302, "slug 중복됨");

        } else {
            Store store = Store.builder()
                    .name(form.getName())
                    .tel(form.getTel())
                    .slug(form.getSlug())
                    .wayTo(form.getWayto())
                    .description(form.getDescription())
                    .previewImage(form.getPreview_image())
                    .isApproved(false)
                    .build();

            storeRepo.save(store);

            StoreLocation storeLocation = StoreLocation.builder()
                    .address(form.getAddress())
                    .latitude(form.getCoordinate().getLatitude())
                    .longitude(form.getCoordinate().getLongitude())
                    .store(store)
                    .build();

            storeLocationRepo.save(storeLocation);

            Collection<String> images = form.getStore_image();

            for (String image: images) {
                storeImageRepo.save(StoreImage.builder().imageUrl(image).store(store).build());
            }

            return new StatusCodeDto(1300, "매장 승인 요청 성공");
        }
    }

    //TODO slug중복확인-서비스
    public StoreCreate.slugCheckDto slugCheck(String slug) {
        return new StoreCreate.slugCheckDto();
    }

    //TODO 세차장승인상태확인-서비스
    public StoreCreate.isApprovedDto isApproved(String slug) {
        return new StoreCreate.isApprovedDto();
    }
}
