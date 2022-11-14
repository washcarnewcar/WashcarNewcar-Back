package me.washcar.wcnc.dto;

import com.sun.istack.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.washcar.wcnc.entity.Brand;
import me.washcar.wcnc.entity.Model;

public class VehicleDataDto {

  @Getter
  @Builder
  public static class BrandListDto {

    @NotNull
    private Long number;

    @NotNull
    private String name;

    public static BrandListDto from(Brand brand) {
      return BrandListDto.builder().number(brand.getBrandId()).name(brand.getName())
          .build();
    }

  }

  @Getter
  @Setter
  public static class BrandListResult<T> {

    private List<T> brand;

    public static <T> BrandListResult<T> getBrandListResult(List<T> list) {
      BrandListResult<T> result = new BrandListResult<>();
      result.setBrand(list);
      return result;
    }
  }

  @Getter
  @Builder
  public static class ModelListDto {

    @NotNull
    private Long number;
    @NotNull
    private String name;

    public static ModelListDto from(Model model) {
      return ModelListDto.builder().number(model.getModelId()).name(model.getName())
          .build();
    }
  }

  @Getter
  @Setter
  public static class ModelListResult<T> {

    private List<T> model;

    public static <T> ModelListResult<T> getModelListResult(List<T> list) {
      ModelListResult<T> result = new ModelListResult<>();
      result.setModel(list);
      return result;
    }
  }
}
