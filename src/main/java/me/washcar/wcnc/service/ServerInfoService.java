package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.PingPongDto;
import me.washcar.wcnc.dto.StatusDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServerInfoService {
    public PingPongDto makePingPongDto() {
        PingPongDto pingPongDto = new PingPongDto();
        log.info(pingPongDto.toString());
        return pingPongDto;
    }

    public StatusDto makeStatusDto(Long id) {
        StatusDto statusDto = new StatusDto(id);
        log.info(statusDto.toString());
        return statusDto;
    }
}
