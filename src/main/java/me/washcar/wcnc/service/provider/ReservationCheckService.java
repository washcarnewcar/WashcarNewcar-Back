package me.washcar.wcnc.service.provider;

import me.washcar.wcnc.dto.provider.ReservationCheck;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class ReservationCheckService {

    //TODO 세차예약요청리스트-서비스
    public ReservationCheck.listDto list(String slug) {
        return new ReservationCheck.listDto();
    }

    //TODO 세차예약요청상세-서비스

    public ReservationCheck.detailDto detail(String slug, long reservationNumber) {
        return new ReservationCheck.detailDto();
    }

    //TODO 예약요청확인-서비스
    //TODO 예약요청거부-서비스
    public ReservationCheck.responseDto response(String slug, long reservationNumber, String body) {
        //TODO body-mapping
        return new ReservationCheck.responseDto();
    }
}
