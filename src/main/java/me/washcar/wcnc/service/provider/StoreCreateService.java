package me.washcar.wcnc.service.provider;

import me.washcar.wcnc.dto.provider.StoreCreate;
import org.springframework.stereotype.Service;

@Service
public class StoreCreateService {
    //TODO 세차장승인요청-서비스
    public StoreCreate.requestDto request(String body) {
        //TODO body-mapping
        return new StoreCreate.requestDto();
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
