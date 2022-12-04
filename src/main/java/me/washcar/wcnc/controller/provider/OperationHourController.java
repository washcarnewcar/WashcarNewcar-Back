package me.washcar.wcnc.controller.provider;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionMessage;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionRequestDto;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionResponseDto;
import me.washcar.wcnc.dto.provider.OperationHour;
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
    public OperationHour.settingDto setting(@PathVariable String slug, @RequestBody String body) {
        return operationHourService.setting(slug, body);
    }

    @PostMapping("/provider/{slug}/except")
    public ReservationExceptionMessage setReservationException(@PathVariable String slug, @RequestBody ReservationExceptionRequestDto requestDto) {
        return reservationExceptionService.setReservationException(slug, requestDto);
    }

    @GetMapping("/provider/{slug}/except")
    public ReservationExceptionResponseDto getReservationException(@PathVariable String slug) {
        return reservationExceptionService.getReservationException(slug);
    }
}
