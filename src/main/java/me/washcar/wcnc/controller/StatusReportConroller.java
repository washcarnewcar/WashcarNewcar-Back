package me.washcar.wcnc.controller;

import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.PingPongDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StatusReportConroller {
    @GetMapping("/ping")
    public PingPongDto pingPong() {
        PingPongDto pingPongDto = new PingPongDto();
        return pingPongDto;
    }

}