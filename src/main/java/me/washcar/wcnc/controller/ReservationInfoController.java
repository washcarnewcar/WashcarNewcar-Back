package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.ReservationInfo;
import me.washcar.wcnc.service.ReservationInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationInfoController {

    private final ReservationInfoService reservationInfoService;

    @GetMapping("/find/{phoneNumber}")
    public ReservationInfo.checkWithPhoneDto checkWithPhone(@PathVariable String phoneNumber){
        return reservationInfoService.checkWithPhone(phoneNumber);
    }

    @GetMapping("/reservation/{reservationNumber}")
    public ReservationInfo.checkWithReservationNumberDto checkWithReservationNumber(@PathVariable String reservationNumber){
        return reservationInfoService.checkWithReservationNumber(reservationNumber);
    }
}
