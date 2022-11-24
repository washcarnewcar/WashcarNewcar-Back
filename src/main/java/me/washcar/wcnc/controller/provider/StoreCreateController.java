package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto._StatusCodeDto;
import me.washcar.wcnc.dto.provider.StoreCreate;
import me.washcar.wcnc.dto.provider.StoreCreate.StoreAllInfoDto;
import me.washcar.wcnc.form.NewStoreCreationForm;
import me.washcar.wcnc.service.provider.StoreCreateService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreCreateController {

  private final StoreCreateService storeCreateService;

  @PostMapping("/provider/new")
  public _StatusCodeDto request(@RequestBody NewStoreCreationForm form) {
    return storeCreateService.request(form);
  }

  @PostMapping("/provider/{slug}/store")
  public _StatusCodeDto update(@PathVariable String slug, @RequestBody NewStoreCreationForm form) {
    return storeCreateService.update(form, slug);
  }

  @GetMapping("/provider/{slug}/store")
  public StoreAllInfoDto getStoreInfo(@PathVariable String slug){
    return storeCreateService.getStoreInfo(slug);
  }

  @GetMapping("/provider/check-slug/{slug}")
  public _StatusCodeDto slugCheck(@PathVariable String slug) {
    return storeCreateService.slugCheck(slug);
  }

  @GetMapping("/provider/{slug}/approve")
  public StoreCreate.isApprovedDto isApproved(@PathVariable String slug) {
    return storeCreateService.isApproved(slug);
  }

  @GetMapping("/provider/slug")
  public StoreCreate.getSlugDto getSlug() {
    return storeCreateService.getSlug();
  }
}


