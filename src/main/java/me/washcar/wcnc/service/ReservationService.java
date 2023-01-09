package me.washcar.wcnc.service;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static me.washcar.wcnc.util.Operation.NOT_OPERATION;
import static me.washcar.wcnc.util.Operation.OPERATION;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.ReservationDto;
import me.washcar.wcnc.dto.ReservationDto.MenuInfoDto;
import me.washcar.wcnc.dto.ReservationDto.ReservationRequestDto;
import me.washcar.wcnc.dto.ReservationDto.ReservationResultDto;
import me.washcar.wcnc.entity.Model;
import me.washcar.wcnc.entity.Reservation;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreMenu;
import me.washcar.wcnc.entity.StoreOperateTime;
import me.washcar.wcnc.entity.StoreReservationException;
import me.washcar.wcnc.entity.User;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.exception.ErrorCode;
import me.washcar.wcnc.repository.ModelRepository;
import me.washcar.wcnc.repository.ReservationRepository;
import me.washcar.wcnc.repository.StoreMenuRepository;
import me.washcar.wcnc.repository.StoreRepository;
import me.washcar.wcnc.repository.UserRepository;
import me.washcar.wcnc.util.Operation;
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

  private final ModelRepository modelRepository;

  private final UserRepository userRepository;

  // 메뉴에대한정보-서비스
  public MenuInfoDto menuInfo(String menuId) {
    // TODO : 승인되지 않은 세차장에 대해서는 조회되면 안됩니다.
    StoreMenu storeMenu = storeMenuRepository.findById(Long.parseLong(menuId))
        .orElseThrow(() -> new CustomException(
            ErrorCode.STORE_MENU_NOT_FOUND));
    return MenuInfoDto.from(storeMenu);
  }

  //TODO 가능한날짜리스트-서비스
  public ReservationDto.menuAvailableDateDto menuAvailableDate(String slug, String menuNumber) {
    return new ReservationDto.menuAvailableDateDto();
  }

  //TODO 날짜별예약가능한시간리스트-서비스
  public ReservationDto.menuAvailableTimeDto menuAvailableTime(String slug, String menuNumber,
      String date) {
    return new ReservationDto.menuAvailableTimeDto();
  }

  // 세차예약요청-서비스
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
      return ReservationResultDto.from(1601, "필수 정보가 입력되지 않음", null);
    }
    if (isAvailableTime(requestDto.getDate(), store) == false) {
      return ReservationResultDto.from(1602, "불가능한 시간에 요청", null);
    }
    Reservation reservation = generateReservation(store, storeMenu, user, model, requestDto);
    store.addReservation(reservation);
    return ReservationResultDto.from(1600, "예약 성공", reservation);
  }

  private boolean isRequestValidate(ReservationRequestDto requestDto) {
    return requestDto.getTel() != null && requestDto.getDate() != null
        && requestDto.getCar_number() != null;
  }

  private boolean isAvailableTime(LocalDateTime date, Store store) {
    if (isOperateDateTime(date, store) == OPERATION) {
      return checkOperateExceptionTime(date, store) != NOT_OPERATION;
    }
    return false;
  }

  private Operation isOperateDateTime(LocalDateTime date, Store store) {
    DayOfWeek value = date.getDayOfWeek(); // 요청한 날의 요일 정보
    int hour = date.getHour(); // 요청한 날의 시간 정보
    int minute = date.getMinute(); // 요청한 날의 분 정보
    StoreOperateTime storeOperateTime = store.getStoreOperateTime();
    if (value == MONDAY) {
      if (storeOperateTime.getMondayStartTime() == null) { // 해당 요일 운영을 안 하는 경우
        return NOT_OPERATION;
      }
      if (checkOperateTime(hour, minute, storeOperateTime.getMondayStartTime(),
          storeOperateTime.getMondayEndTime()) == NOT_OPERATION) { // 해당 요일 운영 시간이 아닐 경우
        return NOT_OPERATION;
      }
    }
    if (value == TUESDAY) {
      if (storeOperateTime.getTuesdayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(hour, minute, storeOperateTime.getTuesdayStartTime(),
          storeOperateTime.getTuesdayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == WEDNESDAY) {
      if (storeOperateTime.getWednesdayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(hour, minute, storeOperateTime.getWednesdayStartTime(),
          storeOperateTime.getWednesdayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == THURSDAY) {
      if (storeOperateTime.getThursdayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(hour, minute, storeOperateTime.getThursdayStartTime(),
          storeOperateTime.getThursdayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == FRIDAY) {
      if (storeOperateTime.getFridayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(hour, minute, storeOperateTime.getFridayStartTime(),
          storeOperateTime.getFridayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == SATURDAY) {
      if (storeOperateTime.getSaturdayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(hour, minute, storeOperateTime.getSaturdayStartTime(),
          storeOperateTime.getSaturdayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    if (value == SUNDAY) {
      if (storeOperateTime.getSundayStartTime() == null) {
        return NOT_OPERATION;
      }
      if (checkOperateTime(hour, minute, storeOperateTime.getSundayStartTime(),
          storeOperateTime.getSundayEndTime()) == NOT_OPERATION) {
        return NOT_OPERATION;
      }
    }
    return OPERATION;
  }

  private Operation checkOperateTime(int hour, int minute, String startTime,
      String endTime) {
    int startHour = Integer.parseInt(startTime.substring(0, 2));
    int startMinute = Integer.parseInt(startTime.substring(3, 5));
    int endHour = Integer.parseInt(endTime.substring(0, 2));
    int endMinute = Integer.parseInt(endTime.substring(3, 5));

    if (hour < startHour || hour > endHour) {
      return NOT_OPERATION;
    }
    if (hour == startHour && minute < startMinute) {
      return NOT_OPERATION;
    }
    if (hour == endHour && minute > endMinute) {
      return NOT_OPERATION;
    }
    return OPERATION;
  }

  private Operation checkOperateExceptionTime(LocalDateTime date, Store store) {
    List<StoreReservationException> storeReservationExceptions = store.getStoreReservationExceptions();
    if (storeReservationExceptions.isEmpty()) { // 운영 예외 시간이 없다면 괜찮다
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
      ReservationRequestDto requestDto) {
    return reservationRepository.save(Reservation.builder()
        .tel(requestDto.getTel())
        .date(requestDto.getDate())
        .request(requestDto.getRequest())
        .carNumber(requestDto.getCar_number())
        .model(model)
        .user(user)
        .store(store)
        .storeMenu(storeMenu)
        .build());
  }
}
