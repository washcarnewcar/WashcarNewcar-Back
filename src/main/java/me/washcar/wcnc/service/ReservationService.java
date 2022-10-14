package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.ReservationDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationService {
    //TODO 메뉴에대한정보-서비스
    public ReservationDto.menuInfo menuInfo(String slug, String menuNumber) {
        return new ReservationDto.menuInfo();
    }
    //TODO 가능한날짜리스트-서비스
    public ReservationDto.menuAvailableDate menuAvailableDate(String slug, String menuNumber) {
        return new ReservationDto.menuAvailableDate();
    }
    //TODO 날짜별예약가능한시간리스트-서비스
    public ReservationDto.menuAvailableTime menuAvailableTime(String slug, String menuNumber, String date) {
        return new ReservationDto.menuAvailableTime();
    }
    //TODO 세차예약요청-서비스
    public ReservationDto.reservation reservation(String slug, String menuNumber) {
        return new ReservationDto.reservation();
    }
}
