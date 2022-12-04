package me.washcar.wcnc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.Reservation;
import me.washcar.wcnc.dto.Reservation.MenuInfoDto;
import me.washcar.wcnc.entity.StoreMenu;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.exception.ErrorCode;
import me.washcar.wcnc.repository.StoreMenuRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

  private final StoreMenuRepository storeMenuRepository;

  // 메뉴에대한정보-서비스
  public MenuInfoDto menuInfo(String menuId) {
    // TODO : 승인되지 않은 세차장에 대해서는 조회되면 안됩니다.
    StoreMenu storeMenu = storeMenuRepository.findById(Long.parseLong(menuId))
        .orElseThrow(() -> new CustomException(
            ErrorCode.STORE_MENU_NOT_FOUND));
    return MenuInfoDto.from(storeMenu);
  }

  //TODO 가능한날짜리스트-서비스
  public Reservation.menuAvailableDateDto menuAvailableDate(String slug, String menuNumber) {
    return new Reservation.menuAvailableDateDto();
  }

  //TODO 날짜별예약가능한시간리스트-서비스
  public Reservation.menuAvailableTimeDto menuAvailableTime(String slug, String menuNumber,
      String date) {
    return new Reservation.menuAvailableTimeDto();
  }

  //TODO 세차예약요청-서비스
  public Reservation.reservationDto reservation(String slug, String menuNumber, String body) {
    //TODO body-mapping
    return new Reservation.reservationDto();
  }
}
