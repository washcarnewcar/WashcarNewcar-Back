package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.ReservationInfo.ReservationDetailDto;
import me.washcar.wcnc.dto.ReservationInfo.ReservationWithTelDto;
import me.washcar.wcnc.dto.ReservationInfo.ReservationWithTelListResult;
import me.washcar.wcnc.service.ReservationInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationInfoController {

  private final ReservationInfoService reservationInfoService;

  @GetMapping("/find")
  public ReservationWithTelListResult<ReservationWithTelDto> getReservationsWithTel(
      @RequestParam String tel) {
    return ReservationWithTelListResult.getReservationListResult(reservationInfoService.getReservationsWithTel(tel));
  }

  @GetMapping("/reservation/{reservationId}")
  public ReservationDetailDto checkWithReservationNumber(
      @PathVariable String reservationId) {
    return reservationInfoService.checkWithReservationNumber(reservationId);
  }
}
