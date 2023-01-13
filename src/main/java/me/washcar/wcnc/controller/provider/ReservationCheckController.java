package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.ReservationCheck.ApproveRequestDto;
import me.washcar.wcnc.dto.provider.ReservationCheck.ApproveResponseDto;
import me.washcar.wcnc.dto.provider.ReservationCheck.RequestReservationDto;
import me.washcar.wcnc.dto.provider.ReservationCheck.ReservationDetailDto;
import me.washcar.wcnc.dto.provider.ReservationCheck.ReservationListResult;
import me.washcar.wcnc.service.provider.ReservationCheckService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationCheckController {

  private final ReservationCheckService reservationCheckService;

  @GetMapping("/provider/{slug}/request")
  public ReservationListResult<RequestReservationDto> getRequestReservations(
      @PathVariable String slug) {
    return ReservationListResult.getReservationListResult(
        reservationCheckService.getRequestReservations(slug));
  }

  @GetMapping("/provider/{slug}/reservation/{reservationId}")
  public ReservationDetailDto getReservationDetail(@PathVariable String slug, @PathVariable String reservationId) {
    return reservationCheckService.getReservationDetail(slug, reservationId);
  }

  @PostMapping(value = "/provider/{slug}/reservation/{reservationId}/request")
  public ApproveResponseDto approveRequest(
      @PathVariable String slug,
      @PathVariable String reservationId,
      @RequestBody ApproveRequestDto requestDto
  ) {
    return reservationCheckService.approveRequest(slug, reservationId, requestDto);
  }
}
