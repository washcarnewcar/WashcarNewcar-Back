package me.washcar.wcnc.service.provider;

import me.washcar.wcnc.dto.provider.Schedule;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    //TODO 세차스케줄리스트-서비스
    public Schedule.listDto list(String slug) {
        return new Schedule.listDto();
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
