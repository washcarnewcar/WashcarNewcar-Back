package me.washcar.wcnc.service.provider;

import static me.washcar.wcnc.entity.ReservationStatus.Reservation_Status.RESERVATION;
import static me.washcar.wcnc.exception.ErrorCode.STORE_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.provider.ReservationCheck.RequestReservationDto;
import me.washcar.wcnc.dto.provider.Schedule;
import me.washcar.wcnc.entity.Reservation;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.repository.ReservationRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

  private final StoreRepository storeRepository;

  private final ReservationRepository reservationRepository;

  // 세차스케줄리스트-서비스
  public List<RequestReservationDto> getStoreSchedules(String slug) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(STORE_NOT_FOUND));

    List<Reservation> reservations = reservationRepository
        .findAllByStore_IdAndReservationStatus_Id(store.getId(), RESERVATION.getId())
        .orElse(new ArrayList<>());
    return reservations.stream().map(RequestReservationDto::from).collect(Collectors.toList());
  }

  //TODO 세차스케줄상세-서비스
  public Schedule.detailDto detail(String slug, long reservationNumber) {
    return new Schedule.detailDto();
  }

  //TODO 세차스케줄거부-서비스
  public Schedule.deleteDto delete(String slug, long reservationNumber, String body) {
    //TODO body-mapping
    return new Schedule.deleteDto();
  }
}
