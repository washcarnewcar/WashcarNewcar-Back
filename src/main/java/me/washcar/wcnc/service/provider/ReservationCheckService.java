package me.washcar.wcnc.service.provider;

import static me.washcar.wcnc.entity.ReservationStatus.Reservation_Status.REQUEST;
import static me.washcar.wcnc.exception.ErrorCode.RESERVATION_NOT_FOUND;
import static me.washcar.wcnc.exception.ErrorCode.STORE_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.ReservationCheck;
import me.washcar.wcnc.dto.provider.ReservationCheck.RequestReservationDto;
import me.washcar.wcnc.dto.provider.ReservationCheck.ReservationDetailDto;
import me.washcar.wcnc.entity.Reservation;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.repository.ReservationRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCheckService {

  private final StoreRepository storeRepository;

  private final ReservationRepository reservationRepository;

  // 세차예약요청리스트-서비스
  public List<RequestReservationDto> getRequestReservations(String slug) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

    List<Reservation> reservations = reservationRepository
        .findAllByStore_IdAndReservationStatus_Id(store.getId(), REQUEST.getId())
        .orElse(new ArrayList<>());
    return reservations.stream().map(RequestReservationDto::from).collect(Collectors.toList());
  }

  // 세차예약요청상세-서비스

  public ReservationDetailDto getReservationDetail(String slug, String reservationId) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

    Reservation reservation = reservationRepository.findById(Long.parseLong(reservationId))
        .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

    return ReservationDetailDto.from(reservation);
  }

  //TODO 예약요청확인-서비스
  //TODO 예약요청거부-서비스
  public ReservationCheck.responseDto response(String slug, long reservationNumber, String body) {
    //TODO body-mapping
    return new ReservationCheck.responseDto();
  }


}
