package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.OperationHour;
import me.washcar.wcnc.service.provider.OperationHourService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OperationHourController {

    private final OperationHourService operationHourService;

    @PostMapping("/provider/{slug}/time")
    public OperationHour.settingDto setting(@PathVariable String slug, @RequestBody String body) {
        return operationHourService.setting(slug, body);
    }

    @PostMapping("/provider/{slug}/except")
    public OperationHour.exceptionSettingDto exceptionSettingDto(@PathVariable String slug, @RequestBody String body) {
        return operationHourService.exceptionSetting(slug, body);
    }
}
