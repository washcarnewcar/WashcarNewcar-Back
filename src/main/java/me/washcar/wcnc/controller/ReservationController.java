package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.Reservation;
import me.washcar.wcnc.dto.Reservation.MenuInfoDto;
import me.washcar.wcnc.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {

  private final ReservationService reservationService;

  @GetMapping(value = "/store/menu/{menuId}")
  public MenuInfoDto getMenuInfo(@PathVariable String menuId) {
    return reservationService.menuInfo(menuId);
  }

  @GetMapping("/store/{slug}/menu/{menuNumber}/available")
  public Reservation.menuAvailableDateDto menuAvailable(@PathVariable String slug,
      @PathVariable String menuNumber) {
    return reservationService.menuAvailableDate(slug, menuNumber);
  }

  @GetMapping("/store/{slug}/menu/{menuNumber}/available/{date}")
  public Reservation.menuAvailableTimeDto menuAvailableTime(@PathVariable String slug,
      @PathVariable String menuNumber, @PathVariable String date) {
    return reservationService.menuAvailableTime(slug, menuNumber, date);
  }

  @RequestMapping(value = "/store/{slug}/menu/{menuNumber}", method = RequestMethod.POST)
  public Reservation.reservationDto reservation(@PathVariable String slug,
      @PathVariable String menuNumber, @RequestBody String body) {
    return reservationService.reservation(slug, menuNumber, body);
  }
}
