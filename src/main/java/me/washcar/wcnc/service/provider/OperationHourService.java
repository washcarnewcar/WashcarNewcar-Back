package me.washcar.wcnc.service.provider;

import me.washcar.wcnc.dto.provider.OperationHour;
import org.springframework.stereotype.Service;

@Service
public class OperationHourService {

    //TODO 운영시간설정-서비스
    public OperationHour.settingDto setting(String slug, String body) {
        //TODO body-mapping
        return new OperationHour.settingDto();
    }

    //TODO 운영시간예외설정-서비스
    public OperationHour.exceptionSettingDto exceptionSetting(String slug, String body) {
        //TODO body-mapping
        return new OperationHour.exceptionSettingDto();
    }
}
