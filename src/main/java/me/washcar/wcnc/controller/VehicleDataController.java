package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.VehicleDataDto.BrandListResult;
import me.washcar.wcnc.dto.VehicleDataDto.BrandDto;
import me.washcar.wcnc.dto.VehicleDataDto.ModelDto;
import me.washcar.wcnc.dto.VehicleDataDto.ModelListResult;
import me.washcar.wcnc.service.VehicleDataService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VehicleDataController {

  private final VehicleDataService vehicleDataService;

  @GetMapping("/car/brand")
  public BrandListResult<BrandDto> menuInfo() {
    return BrandListResult.getBrandListResult(vehicleDataService.brandList());
  }

  @GetMapping("/car/brand/{brandId}")
  public ModelListResult<ModelDto> menuInfo(@PathVariable Long brandId) {
    return ModelListResult.getModelListResult(vehicleDataService.modelList(brandId));
  }
}
