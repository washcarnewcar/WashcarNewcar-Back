package me.washcar.wcnc.service.provider;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto.StoreOperateTimeDto.OperateTimeDto;
import me.washcar.wcnc.dto.StoreOperateTimeDto.StoreOperateTimeResponseDto;
import me.washcar.wcnc.dto.provider.OperationHour.SetOperationHourMessageDto;
import me.washcar.wcnc.entity.Store;
import me.washcar.wcnc.entity.StoreOperateTime;
import me.washcar.wcnc.exception.CustomException;
import me.washcar.wcnc.exception.ErrorCode;
import me.washcar.wcnc.repository.StoreOperateTimeRepository;
import me.washcar.wcnc.repository.StoreRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationHourService {

  private final StoreRepository storeRepository;

  private final StoreOperateTimeRepository storeOperateTimeRepository;

  // 세차장 운영시간설정 서비스
  public SetOperationHourMessageDto setStoreOperateTime(String slug,
      StoreOperateTimeResponseDto requestDto) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    StoreOperateTime storeOperateTime = storeOperateTimeRepository.findByStore(store)
        .orElse(new StoreOperateTime());

    try {
      checkOperateTimeValidate(requestDto);
      storeOperateDateTime(storeOperateTime, requestDto);
      storeOperateTime.setStore(store);
      store.setStoreOperateTime(storeOperateTime);
      storeOperateTimeRepository.save(storeOperateTime);
    } catch (DateTimeException dateTimeException) {
      return SetOperationHourMessageDto.from(2001, dateTimeException.getMessage());
    } catch (Exception e) {
      return SetOperationHourMessageDto.from(2002, e.getMessage());
    }
    return SetOperationHourMessageDto.from(2000, "세차장 운영시간 설정 완료");
  }

  private void storeOperateDateTime(StoreOperateTime storeOperateTime,
      StoreOperateTimeResponseDto requestDto) {
    storeOperateTime.setSundayStartTime(
        requestDto.getSunday().getStart());
    storeOperateTime.setSundayEndTime(requestDto.getSunday().getEnd());

    storeOperateTime.setMondayStartTime(requestDto.getMonday().getStart());
    storeOperateTime.setMondayEndTime(requestDto.getMonday().getEnd());

    storeOperateTime.setTuesdayStartTime(requestDto.getTuesday().getStart());
    storeOperateTime.setTuesdayEndTime(requestDto.getTuesday().getEnd());

    storeOperateTime.setWednesdayStartTime(requestDto.getWednesday().getStart());
    storeOperateTime.setWednesdayEndTime(requestDto.getWednesday().getEnd());

    storeOperateTime.setThursdayStartTime(requestDto.getThursday().getStart());
    storeOperateTime.setThursdayEndTime(requestDto.getThursday().getEnd());

    storeOperateTime.setFridayStartTime(requestDto.getFriday().getStart());
    storeOperateTime.setFridayEndTime(requestDto.getFriday().getEnd());

    storeOperateTime.setSaturdayStartTime(requestDto.getSaturday().getStart());
    storeOperateTime.setSaturdayEndTime(requestDto.getSaturday().getEnd());
  }

  private void checkOperateTimeValidate(StoreOperateTimeResponseDto requestDto)
      throws DateTimeException {
    checkTimeValidate(requestDto.getSunday());
    checkTimeValidate(requestDto.getMonday());
    checkTimeValidate(requestDto.getTuesday());
    checkTimeValidate(requestDto.getWednesday());
    checkTimeValidate(requestDto.getThursday());
    checkTimeValidate(requestDto.getFriday());
    checkTimeValidate(requestDto.getSaturday());
  }

  private void checkTimeValidate(OperateTimeDto day) throws DateTimeException {
    if (day.getStart() == null && day.getEnd() == null) {
      return;
    }
    if ((day.getStart() == null && day.getEnd() != null) || (day.getStart() != null
        && day.getEnd() == null)) {
      throw new DateTimeException("시작시간과 끝 시간 둘 다 입력하세요.");
    }
    LocalDateTime start = LocalDateTime.of(2023, 1, 1,
        Integer.parseInt(day.getStart().substring(0, 2)),
        Integer.parseInt(day.getStart().substring(3, 5)));
    LocalDateTime end = LocalDateTime.of(2023, 1, 1,
        Integer.parseInt(day.getEnd().substring(0, 2)),
        Integer.parseInt(day.getEnd().substring(3, 5)));
    if (start.isAfter(end)) {
      throw new DateTimeException("시작시간이 끝시간보다 뒤에 있음");
    } else if (start.isEqual(end)) {
      throw new DateTimeException("시작시간과 끝시간이 동일함");
    }
  }

  // 세차장 운영시간 조회 서비스
  public StoreOperateTimeResponseDto getStoreOperateTime(String slug) {
    Store store = storeRepository.findBySlug(slug)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
    StoreOperateTime storeOperateTime = storeOperateTimeRepository.findByStore(store)
        .orElseThrow(() -> new CustomException(ErrorCode.STORE_OPERATION_TIME_NOT_FOUND));

    return StoreOperateTimeResponseDto.from(
        generateOperateTimeDto(storeOperateTime.getSundayStartTime(),
            storeOperateTime.getSundayEndTime()),
        generateOperateTimeDto(storeOperateTime.getMondayStartTime(),
            storeOperateTime.getMondayEndTime()),
        generateOperateTimeDto(storeOperateTime.getTuesdayStartTime(),
            storeOperateTime.getTuesdayEndTime()),
        generateOperateTimeDto(storeOperateTime.getWednesdayStartTime(),
            storeOperateTime.getWednesdayEndTime()),
        generateOperateTimeDto(storeOperateTime.getThursdayStartTime(),
            storeOperateTime.getThursdayEndTime()),
        generateOperateTimeDto(storeOperateTime.getFridayStartTime(),
            storeOperateTime.getFridayEndTime()),
        generateOperateTimeDto(storeOperateTime.getSaturdayStartTime(),
            storeOperateTime.getSaturdayEndTime())
    );
  }

  private OperateTimeDto generateOperateTimeDto(String startTime, String endTime) {
    return OperateTimeDto.builder()
        .start(startTime)
        .end(endTime)
        .build();
  }
}
