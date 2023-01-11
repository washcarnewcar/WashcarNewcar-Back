package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.ReservationCheck.RequestReservationDto;
import me.washcar.wcnc.dto.provider.Schedule;
import me.washcar.wcnc.dto.provider.Schedule.ScheduleListResult;
import me.washcar.wcnc.service.provider.ScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

  private final ScheduleService scheduleService;

  @GetMapping("/provider/{slug}/schedule")
  public ScheduleListResult<RequestReservationDto> getStoreSchedules(@PathVariable String slug) {
    return ScheduleListResult.getScheduleListResult(scheduleService.getStoreSchedules(slug));
  }

  @RequestMapping(value = "/provider/{slug}/schedule/{reservationNumber}", method = RequestMethod.GET)
  public Schedule.detailDto detail(
      @PathVariable String slug,
      @PathVariable long reservationNumber
  ) {
    return scheduleService.detail(slug, reservationNumber);
  }

  @RequestMapping(value = "/provider/{slug}/schedule/{reservationNumber}", method = RequestMethod.DELETE)
  public Schedule.deleteDto delete(
      @PathVariable String slug,
      @PathVariable long reservationNumber,
      @RequestBody String body
  ) {
    return scheduleService.delete(slug, reservationNumber, body);
  }
}
