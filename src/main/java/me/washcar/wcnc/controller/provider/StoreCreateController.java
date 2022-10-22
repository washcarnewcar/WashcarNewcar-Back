package me.washcar.wcnc.controller.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.StoreCreate;
import me.washcar.wcnc.service.provider.StoreCreateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreCreateController {

    private final StoreCreateService storeCreateService;

    @PostMapping("/provider/new")
    public StoreCreate.requestDto request(@RequestBody String body) {
        return storeCreateService.request(body);
    }

    @GetMapping("/provider/check-slug/{slug}")
    public StoreCreate.slugCheckDto slugCheck(@PathVariable String slug) {
        return storeCreateService.slugCheck(slug);
    }

    @GetMapping("/provider/{slug}/approve")
    public StoreCreate.isApprovedDto isApproved(@PathVariable String slug) {
        return storeCreateService.isApproved(slug);
    }
}


