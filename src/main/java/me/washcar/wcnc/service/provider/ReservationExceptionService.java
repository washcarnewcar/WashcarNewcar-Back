package me.washcar.wcnc.service.provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionDto;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionMessage;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionRequestDto;
import me.washcar.wcnc.dto.ReservationException.ReservationExceptionResponseDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreReservationException;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.exception.ErrorCode;
import me.washcar.wcnc.repository.ReservationExceptionRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationExceptionService {

  private final StoreRepository storeRepository;

  private final ReservationExceptionRepository reservationExceptionRepository;

  public ReservationExceptionMessage setReservationException(String slug,
      ReservationExceptionRequestDto requestDto) {
    try {
      Store store = storeRepository.findBySlug(slug)
          .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
      List<ReservationExceptionDto> except = requestDto.getExcept();
      for (ReservationExceptionDto reservationExceptionDto : except) {
        generateReservationException(reservationExceptionDto, store);
      }
    } catch (Exception e) {
      return ReservationExceptionMessage.from(2101, "예외 설정 중 오류 발생");
    }
    return ReservationExceptionMessage.from(2100, "예외 설정 성공");
  }

  private void generateReservationException(ReservationExceptionDto reservationExceptionDto,
      Store store) {
    reservationExceptionRepository.save(
        StoreReservationException.builder().allDay(reservationExceptionDto.isAllday())
            .startDateTime(reservationExceptionDto.getStart())
            .endDateTime(reservationExceptionDto.getEnd()).store(store).build());
  }

  public ReservationExceptionResponseDto getReservationException(String slug) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    List<StoreReservationException> storeReservationExceptions = reservationExceptionRepository.findAllByStore_StoreId(
        store.getStoreId());
    List<ReservationExceptionDto> reservationExceptionDtoStream = storeReservationExceptions.stream()
        .map(ReservationExceptionDto::from).collect(Collectors.toList());
    return ReservationExceptionResponseDto.from(reservationExceptionDtoStream);
  }
}
