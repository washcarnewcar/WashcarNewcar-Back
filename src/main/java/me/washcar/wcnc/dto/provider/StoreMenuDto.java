package me.washcar.wcnc.dto.provider;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import me.washcar.wcnc.entity.StoreMenu;
import org.springframework.lang.Nullable;

public class StoreMenuDto {

  private static final int SUCCESS_CREATE_STATUS = 2200;
  private static final String SUCCESS_CREATE_MESSAGE = "메뉴 생성 성공";

  private static final int SUCCESS_DELETE_STATUS = 2300;
  private static final String SUCCESS_DELETE_MESSAGE = "메뉴 삭제 성공";
  private static final int SUCCESS_UPDATE_STATUS = 2400;
  private static final String SUCCESS_UPDATE_MESSAGE = "메뉴 수정 성공";


  @Getter
  @Builder
  public static class MenuResponseDto {

    @NotNull
    private Long number;
    @NotNull
    private String image;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private int price;
    @NotNull
    private Long expected_hour;
    @NotNull
    private Long expected_minute;

    public static MenuResponseDto from(StoreMenu storeMenu) {
      return MenuResponseDto.builder()
          .number(storeMenu.getStoreMenuId())
          .image(storeMenu.getImage())
          .name(storeMenu.getName())
          .description(storeMenu.getDescription())
          .price(storeMenu.getPrice())
          .expected_hour(storeMenu.getExpected_hour())
          .expected_minute(storeMenu.getExpected_minute())
          .build();
    }
  }

  @Getter
  @Builder
  public static class MenuRequestDto {

    @NotNull
    private String image;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private int price;
    @NotNull
    private Long expected_hour;
    @NotNull
    private Long expected_minute;

    public static MenuRequestDto from(StoreMenu storeMenu) {
      return MenuRequestDto.builder()
          .image(storeMenu.getImage())
          .name(storeMenu.getName())
          .description(storeMenu.getDescription())
          .price(storeMenu.getPrice())
          .expected_hour(storeMenu.getExpected_hour())
          .expected_minute(storeMenu.getExpected_minute())
          .build();
    }
  }

  @Getter
  @Builder
  public static class SuccessCreateDto {

    private int status;
    private String message;
    private Long menu_number;

    public static SuccessCreateDto getSuccessCreateDto(Long storeMenu) {
      return SuccessCreateDto.builder().status(SUCCESS_CREATE_STATUS).message(
              SUCCESS_CREATE_MESSAGE)
          .menu_number(storeMenu).build();
    }
  }

  @Getter
  public static class FailCreateDto {

    private final int status = 2201;
    private final String message = "필수 정보 입력되지 않음";
    @Nullable
    private Long menuNumber = null;
  }

  //TODO 메뉴업데이트-Dto
  @Getter
  @Builder
  public static class UpdateDto {

    private int status;
    private String message;

    public static UpdateDto getUpdateDto() {
      return UpdateDto.builder().status(SUCCESS_UPDATE_STATUS).message(
          SUCCESS_UPDATE_MESSAGE).build();
    }
  }

  @Getter
  @Builder
  public static class DeleteDto {

    private int status;
    private String message;

    public static DeleteDto getDeleteDto() {
      return DeleteDto.builder().status(SUCCESS_DELETE_STATUS).message(
          SUCCESS_DELETE_MESSAGE).build();
    }
  }
}
