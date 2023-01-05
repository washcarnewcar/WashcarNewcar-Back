package me.washcar.wcnc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
  STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "매장을 찾을 수 없습니다."),
  STORE_LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "매장의 위치를 찾을 수 없습니다."),
  STORE_IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "매장의 이미지를 찾을 수 없습니다."),
  STORE_MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "매장의 메뉴를 찾을 수 없습니다."),
  STORE_OPERATION_TIME_NOT_FOUND(HttpStatus.NOT_FOUND, "매장의 운영시간을 찾을 수 없습니다.");




  private final HttpStatus httpStatus;
  private final String message;
}
