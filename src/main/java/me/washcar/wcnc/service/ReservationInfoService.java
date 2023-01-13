package me.washcar.wcnc.service;

import static java.util.stream.Collectors.toList;
import static me.washcar.wcnc.exception.ErrorCode.RESERVATION_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.ReservationInfo.ReservationDetailDto;
import me.washcar.wcnc.dto.ReservationInfo.ReservationWithTelDto;
import me.washcar.wcnc.entity.Reservation;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationInfoService {

  private final ReservationRepository reservationRepository;

  // 휴대폰번호로예약리스트조회-서비스
  public List<ReservationWithTelDto> getReservationsWithTel(String tel) {
    List<Reservation> reservations = reservationRepository.findAllByTel(tel)
        .orElse(new ArrayList<>());
    return reservations.stream().map(ReservationWithTelDto::from).collect(toList());
  }

  // 예약한세차상세조회-서비스
  public ReservationDetailDto checkWithReservationNumber(
      String reservationId) {
    Reservation reservation = reservationRepository.findById(Long.parseLong(reservationId))
        .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

    return ReservationDetailDto.from(reservation);
  }

}
