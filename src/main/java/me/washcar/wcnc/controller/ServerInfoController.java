package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.washcar.wcnc.dto.PingPongDto;
import me.washcar.wcnc.dto.StatusDto;
import me.washcar.wcnc.service.ServerInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ServerInfoController {

    private final ServerInfoService serverInfoService;

    @GetMapping("/status/ping")
    public PingPongDto pingPong() {
        return serverInfoService.makePingPongDto();
    }

    @GetMapping("/status/{id}")
    public StatusDto statusReportPathVariable(@PathVariable Long id) {
        return serverInfoService.makeStatusDto(id);
    }

    @GetMapping("/status/request")
    public StatusDto statusReportRequestParam(@RequestParam("id") Long id) {
        return serverInfoService.makeStatusDto(id);
    }
}
