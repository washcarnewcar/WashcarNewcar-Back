package me.washcar.wcnc.service;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto._PingPongDto;
import me.washcar.wcnc.dto._StatusDto;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class _ServerInfoService {
    public _PingPongDto makePingPongDto() {
        _PingPongDto pingPongDto = new _PingPongDto();
        log.info(pingPongDto.toString());
        return pingPongDto;
    }

    public _StatusDto makeStatusDto(Long id) {
        _StatusDto statusDto = new _StatusDto(id);
        log.info(statusDto.toString());
        return statusDto;
    }
}
