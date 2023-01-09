package me.washcar.wcnc.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.ReservationDto;
import me.washcar.wcnc.dto.ReservationDto.MenuInfoDto;
import me.washcar.wcnc.dto.ReservationDto.ReservationRequestDto;
import me.washcar.wcnc.dto.ReservationDto.ReservationResultDto;
import me.washcar.wcnc.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @GetMapping("/store/menu/{menuId}")
  public MenuInfoDto getMenuInfo(@PathVariable String menuId) {
    return reservationService.menuInfo(menuId);
  }

  @PostMapping("/store/{slug}/menu/{menuId}")
  public ReservationResultDto requestReservation(@PathVariable String slug, @PathVariable String menuId, @RequestBody @Valid ReservationRequestDto requestDto) {
    return reservationService.
        requestReservation(slug, menuId, requestDto);
  }

  @GetMapping("/store/{slug}/menu/{menuNumber}/available")
  public ReservationDto.menuAvailableDateDto menuAvailable(@PathVariable String slug,
      @PathVariable String menuNumber) {
    return reservationService.menuAvailableDate(slug, menuNumber);
  }

  @GetMapping("/store/{slug}/menu/{menuNumber}/available/{date}")
  public ReservationDto.menuAvailableTimeDto menuAvailableTime(@PathVariable String slug,
      @PathVariable String menuNumber, @PathVariable String date) {
    return reservationService.menuAvailableTime(slug, menuNumber, date);
  }
}
