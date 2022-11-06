package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.StatusCodeDto;
import me.washcar.wcnc.dto.provider.StoreCreate;
import me.washcar.wcnc.form.NewStoreCreationForm;
import me.washcar.wcnc.service.provider.StoreCreateService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class StoreCreateController {
    private final StoreCreateService storeCreateService;

    @PostMapping("/provider/new")
    public StatusCodeDto request(@RequestBody NewStoreCreationForm form) {
        return storeCreateService.request(form);
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


