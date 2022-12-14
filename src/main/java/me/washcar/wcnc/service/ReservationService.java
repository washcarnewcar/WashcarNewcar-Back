package me.washcar.wcnc.service;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static me.washcar.wcnc.util.OperationMessage.NOT_OPERATION;
import static me.washcar.wcnc.util.OperationMessage.OPERATION;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.ReservationDto.AvailableDateDto;
import me.washcar.wcnc.dto.ReservationDto.AvailableTimeDto;
import me.washcar.wcnc.dto.ReservationDto.MenuInfoDto;
import me.washcar.wcnc.dto.ReservationDto.ReservationRequestDto;
import me.washcar.wcnc.dto.ReservationDto.ReservationResultDto;
import me.washcar.wcnc.entity.Model;
import me.washcar.wcnc.entity.Reservation;
import me.washcar.wcnc.entity.ReservationStatus;
import me.washcar.wcnc.entity.ReservationStatus.Reservation_Status;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreMenu;
import me.washcar.wcnc.entity.StoreOperateTime;
import me.washcar.wcnc.entity.StoreReservationException;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.exception.ErrorCode;
import me.washcar.wcnc.repository.ModelRepository;
import me.washcar.wcnc.repository.ReservationRepository;
import me.washcar.wcnc.repository.ReservationStatusRepository;
import me.washcar.wcnc.repository.StoreMenuRepository;
import me.washcar.wcnc.repository.StoreRepository;
import me.washcar.wcnc.repository.UserRepository;
import me.washcar.wcnc.util.OperationMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

  private final StoreRepository storeRepository;

  private final StoreMenuRepository storeMenuRepository;

  private final ReservationRepository reservationRepository;

  private final ReservationStatusRepository reservationStatusRepository;
  private final ModelRepository modelRepository;

  private final UserRepository userRepository;

  // ?????????????????????-?????????
  public MenuInfoDto menuInfo(String menuId) {
    // TODO : ???????????? ?????? ???????????? ???????????? ???????????? ????????????.
    StoreMenu storeMenu = storeMenuRepository.findById(Long.parseLong(menuId))
        .orElseThrow(() -> new CustomException(
            ErrorCode.STORE_MENU_NOT_FOUND));
    return MenuInfoDto.from(storeMenu);
  }

  //TODO ????????????????????????-?????????
  public AvailableDateDto getAvailableDays(String slug, String menuNumber) {
    // TODO : ???????????? ?????? ???????????? ???????????? ???????????? ????????????.
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

    LocalDateTime now = LocalDateTime.now();

    return new AvailableDateDto();
  }

  //TODO ???????????????????????????????????????-?????????
  public AvailableTimeDto getAvailableTimes(String slug, String menuNumber,
      String date) {
    // TODO : ???????????? ?????? ???????????? ???????????? ???????????? ????????????.
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    LocalDate day = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

    return new AvailableTimeDto();
  }

  // ??????????????????-?????????
  public ReservationResultDto requestReservation(String slug, String menuId,
      ReservationRequestDto requestDto) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    StoreMenu storeMenu = storeMenuRepository.findById(Long.parseLong(menuId))
        .orElseThrow(() -> new CustomException(
            ErrorCode.STORE_MENU_NOT_FOUND));

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByEmail(authentication.getPrincipal().toString());

    Model model = modelRepository.findById((long) requestDto.getCar_model_number())
        .orElseThrow(() -> new CustomException(ErrorCode.MODEL_NOT_FOUND));

    if (isRequestValidate(requestDto) == false) {
      return ReservationResultDto.from(1601, "?????? ????????? ???????????? ??????", null);
    }
    if (isAvailableTime(requestDto.getDate(), store) == false) {
      return ReservationResultDto.from(1602, "???????????? ????????? ??????", null);
    }
    ReservationStatus reservationStatus = reservationStatusRepository
        .findById(Reservation_Status.REQUEST.getId()).get();
    Reservation reservation = generateReservation(store, storeMenu, user, model, requestDto,
        reservationStatus);
    store.addReservation(reservation);
    return ReservationResultDto.from(1600, "?????? ??????", reservation);
  }

  private boolean isRequestValidate(ReservationRequestDto requestDto) {
    return requestDto.getTel() != null && requestDto.getDate() != null
        && requestDto.getCar_number() != null;
  }

  private boolean isAvailableTime(LocalDateTime date, Store store) {
    if (checkOperateDateTime(date, store) == OPERATION) { // ???????????? ??????
      return checkOperateExceptionTime(date, store) != NOT_OPERATION; // ?????? ?????? ?????? ??????
    }
    return false;
  }

  private OperationMessage checkOperateDateTime(LocalDateTime date, Store store) {
    DayOfWeek value = date.getDayOfWeek(); // ????????? ?????? ?????? ??????
    StoreOperateTime storeOperateTime = store.getStoreOperateTime();
    if (value == MONDAY) {
      if (storeOperateTime.getMondayStartTime() == null) { // ?????? ?????? ????????? ??? ?????? ??????
        return NOT_OPERATION;
      }
      if (checkOperateTime(date, storeOperateTime.getMondayStartTime(),
          storeOperateTime.getMondayEndTime()) == NOT_OPERATION) { // ?????? ?????? ?????? ????????? ?????? ??????
        return NOT_OPERATION;
      }
    }
    if (value == TUESDAY) {
      if (storeOperateTime.getTuesdayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(date, storeOperateTime.getTuesdayStartTime(),
          storeOperateTime.getTuesdayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == WEDNESDAY) {
      if (storeOperateTime.getWednesdayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(date, storeOperateTime.getWednesdayStartTime(),
          storeOperateTime.getWednesdayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == THURSDAY) {
      if (storeOperateTime.getThursdayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(date, storeOperateTime.getThursdayStartTime(),
          storeOperateTime.getThursdayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == FRIDAY) {
      if (storeOperateTime.getFridayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(date, storeOperateTime.getFridayStartTime(),
          storeOperateTime.getFridayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == SATURDAY) {
      if (storeOperateTime.getSaturdayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(date, storeOperateTime.getSaturdayStartTime(),
          storeOperateTime.getSaturdayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == SUNDAY) {
      if (storeOperateTime.getSundayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(date, storeOperateTime.getSundayStartTime(),
          storeOperateTime.getSundayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    return OPERATION;
  }

  private OperationMessage checkOperateTime(LocalDateTime date, LocalTime startTime,
      LocalTime endTime) {
    LocalTime now = LocalTime.of(date.getHour(), date.getMinute());

    if (startTime.isAfter(now) || endTime.isBefore(now)) {
      return NOT_OPERATION;
    }
    return OPERATION;
  }

  private OperationMessage checkOperateExceptionTime(LocalDateTime date, Store store) {
    List<StoreReservationException> storeReservationExceptions = store.getStoreReservationExceptions();
    if (storeReservationExceptions.isEmpty()) { // ?????? ?????? ????????? ????????? ?????????
      return OPERATION;
    }
    for (StoreReservationException exception : storeReservationExceptions) {
      if (exception.getStartDateTime().isBefore(date) && exception.getEndDateTime().isAfter(date)) {
        return NOT_OPERATION;
      }
    }
    return OPERATION;
  }

  private Reservation generateReservation(Store store, StoreMenu storeMenu, User user, Model model,
      ReservationRequestDto requestDto, ReservationStatus reservationStatus) {
    return reservationRepository.save(Reservation.builder()
        .tel(requestDto.getTel())
        .date(requestDto.getDate())
        .request(requestDto.getRequest())
        .carNumber(requestDto.getCar_number())
        .model(model)
        .user(user)
        .store(store)
        .storeMenu(storeMenu)
        .reservationStatus(reservationStatus)
        .build());
  }
}
