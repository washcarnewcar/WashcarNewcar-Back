package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionMessage;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionRequestDto;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionResponseDto;
import me.washcar.wcnc.dto.StoreOperateTimeDto.StoreOperateTimeResponseDto;
import me.washcar.wcnc.dto.provider.OperationHour.SetOperationHourMessageDto;
import me.washcar.wcnc.service.provider.OperationHourService;
import me.washcar.wcnc.service.provider.ReservationExceptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OperationHourController {

  private final OperationHourService operationHourService;

  private final ReservationExceptionService reservationExceptionService;

  @PostMapping("/provider/{slug}/time")
  public SetOperationHourMessageDto setStoreOperateTime(@PathVariable String slug,
      @RequestBody StoreOperateTimeResponseDto requestDto) {
    return operationHourService.setStoreOperateTime(slug, requestDto);
  }

  @GetMapping("/provider/{slug}/time")
  public StoreOperateTimeResponseDto getStoreOperateTime(@PathVariable String slug) {
    return operationHourService.getStoreOperateTime(slug);
  }

  @PostMapping("/provider/{slug}/except")
  public ReservationExceptionMessage setReservationException(@PathVariable String slug,
      @RequestBody ReservationExceptionRequestDto requestDto) {
    return reservationExceptionService.setReservationException(slug, requestDto);
  }

  @GetMapping("/provider/{slug}/except")
  public ReservationExceptionResponseDto getReservationException(@PathVariable String slug) {
    return reservationExceptionService.getReservationException(slug);
  }
}
