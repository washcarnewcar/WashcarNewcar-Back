package me.washcar.wcnc.controller;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.PingPongDto;
import me.washcar.wcnc.dto.StatusDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ServerInfoController {
    @GetMapping("/status/ping")
    public PingPongDto pingPong() {
        PingPongDto pingPongDto = new PingPongDto();
        log.info(pingPongDto.toString());
        return pingPongDto;
    }

    @GetMapping("/status/{id}")
    public StatusDto statusReportPathVariable(@PathVariable Long id) {
        StatusDto statusDto = new StatusDto(id);
        log.info(statusDto.toString());
        return statusDto;
    }

    @GetMapping("/status/request")
    public StatusDto statusReportRequestParam(@RequestParam("id") Long id) {
        StatusDto statusDto = new StatusDto(id);
        log.info(statusDto.toString());
        return statusDto;
    }
}
