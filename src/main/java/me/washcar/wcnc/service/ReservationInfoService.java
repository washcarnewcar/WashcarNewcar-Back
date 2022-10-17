package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.ReservationInfo;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReservationInfoService {
    //TODO 휴대폰번호로예약리스트조회-서비스
    public ReservationInfo.checkWithPhoneDto checkWithPhone(String phoneNumber){
        return new ReservationInfo.checkWithPhoneDto();
    }
    //TODO 예약한세차상세조회-서비스
    public ReservationInfo.checkWithReservationNumberDto checkWithReservationNumber(String reservationNumber){
        return new ReservationInfo.checkWithReservationNumberDto();
    }

}
