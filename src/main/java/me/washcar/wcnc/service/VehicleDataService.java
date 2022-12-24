package me.washcar.wcnc.service;

import static java.util.stream.Collectors.*;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.VehicleDataDto.BrandDto;
import me.washcar.wcnc.dto.VehicleDataDto.ModelDto;
import me.washcar.wcnc.entity.Brand;
import me.washcar.wcnc.entity.Model;
import me.washcar.wcnc.repository.BrandRepository;
import me.washcar.wcnc.repository.ModelRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleDataService {

  private final BrandRepository brandRepository;

  private final ModelRepository modelRepository;

  public List<BrandDto> brandList() {
    List<Brand> brands = brandRepository.findAll();
    return brands.stream().map(BrandDto::from).collect(toList());
  }

  public List<ModelDto> modelList(Long brandId) {
    List<Model> models = modelRepository.findAllByBrand_Id(brandId);
    return models.stream().map(ModelDto::from).collect(toList());
  }
}
