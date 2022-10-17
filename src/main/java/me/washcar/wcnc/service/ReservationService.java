package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.Reservation;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationService {
    //TODO 메뉴에대한정보-서비스
    public Reservation.menuInfoDto menuInfo(String slug, String menuNumber) {
        return new Reservation.menuInfoDto();
    }
    //TODO 가능한날짜리스트-서비스
    public Reservation.menuAvailableDateDto menuAvailableDate(String slug, String menuNumber) {
        return new Reservation.menuAvailableDateDto();
    }
    //TODO 날짜별예약가능한시간리스트-서비스
    public Reservation.menuAvailableTimeDto menuAvailableTime(String slug, String menuNumber, String date) {
        return new Reservation.menuAvailableTimeDto();
    }
    //TODO 세차예약요청-서비스
    public Reservation.reservationDto reservation(String slug, String menuNumber) {
        return new Reservation.reservationDto();
    }
}
