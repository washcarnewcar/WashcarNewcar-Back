package me.washcar.wcnc.service.provider;

import static me.washcar.wcnc.entity.ReservationStatus.Reservation_Status.REQUEST;
import static me.washcar.wcnc.entity.ReservationStatus.Reservation_Status.RESERVATION;
import static me.washcar.wcnc.exception.ErrorCode.RESERVATION_NOT_FOUND;
import static me.washcar.wcnc.exception.ErrorCode.STORE_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.ReservationCheck.ApproveRequestDto;
import me.washcar.wcnc.dto.provider.ReservationCheck.ApproveResponseDto;
import me.washcar.wcnc.dto.provider.ReservationCheck.RequestReservationDto;
import me.washcar.wcnc.dto.provider.ReservationCheck.ReservationDetailDto;
import me.washcar.wcnc.entity.Reservation;
import me.washcar.wcnc.entity.ReservationStatus;
import me.washcar.wcnc.entity.ReservationStatus.Reservation_Status;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.repository.ReservationRepository;
import me.washcar.wcnc.repository.ReservationStatusRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCheckService {

  private final StoreRepository storeRepository;

  private final ReservationRepository reservationRepository;

  private final ReservationStatusRepository statusRepository;

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
    Reservation reservation = reservationRepository.findById(Long.parseLong(reservationId))
        .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));
    return ReservationDetailDto.from(reservation);
  }

  public ApproveResponseDto approveRequest(String slug, String reservationId,
      ApproveRequestDto requestDto) {
    Reservation reservation = reservationRepository.findById(Long.parseLong(reservationId))
        .orElseThrow(() -> new CustomException(RESERVATION_NOT_FOUND));

    ReservationStatus reservationStatus = statusRepository.findById(RESERVATION.getId()).get();

    reservation.setReservationStatus(reservationStatus);
    reservation.setExpectedWashTime(requestDto.getExpected_wash_time());
    return ApproveResponseDto.from(1000, "세차 요청 접수 성공");
  }


}
