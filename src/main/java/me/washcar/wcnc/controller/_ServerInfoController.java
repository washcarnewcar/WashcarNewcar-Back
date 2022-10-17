package me.washcar.wcnc.controller;

import lombok.RequiredArgsConstructor;
import me.washcar.wcnc.dto._PingPongDto;
import me.washcar.wcnc.dto._StatusDto;
import me.washcar.wcnc.service._ServerInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class _ServerInfoController {

    private final _ServerInfoService serverInfoService;

    @GetMapping("/status/ping")
    public _PingPongDto pingPong() {
        return serverInfoService.makePingPongDto();
    }

    @GetMapping("/status/{id}")
    public _StatusDto statusReportPathVariable(@PathVariable Long id) {
        return serverInfoService.makeStatusDto(id);
    }

    @GetMapping("/status/request")
    public _StatusDto statusReportRequestParam(@RequestParam("id") Long id) {
        return serverInfoService.makeStatusDto(id);
    }
}
